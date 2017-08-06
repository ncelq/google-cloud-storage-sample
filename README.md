Google Cloud Storage Upload File Sample
===================

A sample program to store text into file and upload to Google Cloud Storage

Prerequisites
-------------
 - Clone the project from git
 - Create a project in Google Cloud console
 - Create a bucket called "test-43541281"
> **Ref:**
> - https://cloud.google.com/storage/docs/creating-buckets#storage-create-bucket-console
 
 - Create a service account for the project with service owner permission. Store the json private key
 > **Ref:**
> - https://cloud.google.com/iam/docs/creating-managing-service-accounts
 - Store the private key as src/test/resources/key.json file


Run
-------------
JUnit test file **com.cloud.sample.ApplicationTest** is the code snippet to upload file to Google Cloud Storage. 
 > **To run:**
> mvn clean install
