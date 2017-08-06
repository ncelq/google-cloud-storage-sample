package com.cloud.sample;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class ApplicationTest {
	
	public final String BUCKET_NAME = "test-43541281";
	public final String FILE_NAME = "test.txt";
	
	@Test
	public void uploadData() throws IOException {
		InputStream keyStream = this.getClass().getClassLoader().getResourceAsStream("key.json");
		
		// Define the Google cloud storage
		Storage storage = StorageOptions.newBuilder()
			    .setCredentials(ServiceAccountCredentials.fromStream(keyStream))
			    .build()
			    .getService();

		// Upload a file to bucket
		BlobInfo blobInfo =
			      storage.create(
			          BlobInfo
			              .newBuilder(BUCKET_NAME, FILE_NAME)
			              // Modify access list to allow all users with link to read file
			              .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
			              .build(),
			              new ByteArrayInputStream("Hello World".getBytes(StandardCharsets.UTF_8)));
	
		// assert if it is uploaded
		assertThat(blobInfo.getBlobId().getName(), is(FILE_NAME));
		
		boolean deleted = storage.delete(blobInfo.getBlobId());
		
		// assert if it is deleted
		assertThat(deleted, is(true));
		
	}

}
