package edu.wpi.cs.cs3733.flame;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.*;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import edu.wpi.cs.cs3733.flame.db.VideoClipsDAO;
import edu.wpi.cs.cs3733.flame.http.*;
import edu.wpi.cs.cs3733.flame.model.*;

public class UploadClipHandler implements RequestHandler<UploadClipRequest, UploadClipResponse> {
    public LambdaLogger logger;
    public AmazonS3 s3 = null;
    public VideoClipsDAO dao;

    // Note: this works, but it would be better to move this to environment/configuration mechanisms
	// which you don't have to do for this project.
	public static final String REAL_BUCKET = "Video Clips/";
	public static final String TEST_BUCKET = "testconstants/";

    String uploadVideoClip(String filename, byte[]  contents) throws Exception {
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
			logger.log("attach to S3 succeed");
		}

        String bucket = REAL_BUCKET;
        String prefix = "cs3733flame";
		
		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);
		
		// makes the object publicly visible
		PutObjectResult res = s3.putObject(new PutObjectRequest(prefix, bucket + filename, bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		// if we ever get here, then whole thing was stored
		return "https://"+ prefix + ".s3.amazonaws.com/" + bucket + filename;
	}

    @Override 
	public UploadClipResponse handleRequest(UploadClipRequest req, Context context)  {
		logger = context.getLogger();
        UploadClipResponse response = null;
        byte[] encoded = null; 
        try {
        	encoded = java.util.Base64.getDecoder().decode(req.base64EncodedString);
        }
        catch (Exception e) {
        	logger.log(e.getStackTrace()[0].toString());
        }
        String bucketURI;
        try{
            bucketURI = uploadVideoClip(UUID.randomUUID().toString(), encoded);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.log(e.getLocalizedMessage());
            bucketURI = "oopsie";
        }

        VideoClip clip = new VideoClip(bucketURI, req.character, req.transcript, req.remoteAccess);

        dao = new VideoClipsDAO();
        try {
			dao.addClip(clip);
			response = new UploadClipResponse(200);
		} catch (Exception e) {
			logger.log(e.getLocalizedMessage().toString());
			response = new UploadClipResponse(e.getMessage().toString(), 400);
		}
        return response;
    }
}