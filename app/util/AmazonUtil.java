package util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.Logger;
import play.libs.Json;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class AmazonUtil {

    public static final String ACCESS_KEY = "dummy_access_key";
    public static final String SECRET_KEY = "dummy_secret_key";

    public static String MEME_BUCKET_URL =  "dummy_bucket_url";

    public static void sendEmail(String sender, List<String> recipients, String subject, String body)
    {
        Content subjectContent = new Content(subject);
        Destination destination = new Destination(recipients);
        try{
            String mailContent=body;
            Content bodyContent = new Content().withData(mailContent);
            Body msgBody = new Body().withHtml(bodyContent);
            Message msg = new Message(subjectContent, msgBody);
            SendEmailRequest request = new SendEmailRequest(sender, destination, msg);
            AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
            AmazonSimpleEmailServiceClient sesClient = new AmazonSimpleEmailServiceClient(credentials);
            SendEmailResult result = sesClient.sendEmail(request);
            Logger.debug("Email Response" + result.toString());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void upload(ImageBucket bucket, String keyName, File uploadFile)
			throws IOException {

        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        InputStream stream = loader.getResourceAsStream("AwsCredentials.properties");
        InputStream stream = loader.getResourceAsStream("AwsCredentials.properties");
        AmazonS3Client s3client = new AmazonS3Client(new PropertiesCredentials(stream));


        try {
			System.out.println("Uploading a new object to S3 from a file\n");
            PutObjectRequest putObjectRequest = new PutObjectRequest(ImageBucket.getBucketName(), keyName, uploadFile);
            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead); // public for all
            s3client.putObject(putObjectRequest);
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which "
					+ "means your request made it "
					+ "to Amazon S3, but was rejected with an error response"
					+ " for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which "
					+ "means the client encountered "
					+ "an internal error while trying to "
					+ "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}

	/**
	 * Returns a URL that will expire after a few minutes
	 *
	 * @return
	 */
	public static URL getImageURL(ImageBucket bucket, String imageKeyName) {

		AmazonS3Client s3;
		URL presignedUrl = null;
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("AwsCredentials.properties");

		try {
            s3 = new AmazonS3Client(new PropertiesCredentials(stream));

			Date expiry = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(expiry);
			cal.add(Calendar.MINUTE,5);
			expiry = cal.getTime();
			
			presignedUrl = s3.generatePresignedUrl(ImageBucket.getBucketName(), imageKeyName, expiry);
			System.out.println(presignedUrl.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
            Logger.error("AmazonUtil", e);
		}
		return presignedUrl;

	}

    public static void upload(ImageBucket bucket, String keyName, InputStream data, String filePath)
            throws IOException {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("AwsCredentials.properties");
        AmazonS3Client s3client = new AmazonS3Client(new PropertiesCredentials(stream));
   //     s3client.setRegion("us-west");


        try {
            System.out.println("Uploading a new object to S3 from a file\n");

            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest = new PutObjectRequest(ImageBucket.getBucketName(), keyName, fileInputStream, objectMetadata);
            PutObjectResult result = s3client.putObject(putObjectRequest);


            AccessControlList bucketAcl75 = s3client.getBucketAcl(ImageBucket.getBucketName());
            bucketAcl75.grantPermission(GroupGrantee.AllUsers, Permission.FullControl);
            SetBucketAclRequest bucketAclRequest = new SetBucketAclRequest(ImageBucket.getBucketName(),bucketAcl75);
            s3client.setBucketAcl(bucketAclRequest);
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which "
                    + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response"
                    + " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which "
                    + "means the client encountered "
                    + "an internal error while trying to "
                    + "communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    public static InputStream getFileStream(ImageBucket bucket, String imageKeyName) {

        AmazonS3Client s3;
        URL presignedUrl = null;
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("AwsCredentials.properties");

        try {
            s3 = new AmazonS3Client(new PropertiesCredentials(stream));

            Date expiry = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(expiry);
            cal.add(Calendar.MINUTE,5);
            expiry = cal.getTime();
            GetObjectRequest request = new GetObjectRequest(ImageBucket.getBucketName(), imageKeyName);
            S3Object object = s3.getObject(request);
            S3ObjectInputStream objectContent = object.getObjectContent();

            return (InputStream)objectContent;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            Logger.error("AmazonUtil", e);
        }
        return null;

    }

    public static ObjectNode uploadBufferedImageObject(BufferedImage bufferedImage, String filename){
        ObjectNode result = Json.newObject();
        filename+=".jpg";
        try{
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", os);
            byte[] buffer = os.toByteArray();
            InputStream is = new ByteArrayInputStream(buffer);
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("AwsCredentials.properties");
            AmazonS3 s3 = new AmazonS3Client(new PropertiesCredentials(stream));
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(buffer.length);
            meta.setContentType("image/jpg");
            s3.putObject(new PutObjectRequest("sekukids", filename, is, meta).withCannedAcl(CannedAccessControlList.PublicRead));

            result.put("status", "success");
            result.put("url", MEME_BUCKET_URL + filename);

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which "
                    + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response"
                    + " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());

            result.put("status", "error");
            result.put("reason", ase.getErrorMessage());

        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which "
                    + "means the client encountered "
                    + "an internal error while trying to "
                    + "communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            result.put("status", "error");
            result.put("reason", ace.getMessage());
        }catch (Exception ex){
            System.out.println("IO Exception: " + ex.getMessage());
            result.put("status", "error");
            result.put("reason", ex.getMessage());
        }
        return result;
    }

}
