package edu.wpi.cs.cs3733.flame.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AdminTest {

	@Test
	public void adminTest() {
		Administrator admin = new Administrator();
		VideoClip clip = new VideoClip("google.com", "Spock", "Hi", true);
		admin.setAccess(clip, true);
	}
}
