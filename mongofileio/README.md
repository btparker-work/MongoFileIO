# calling in post man
upload binary
  http://localhost:8081/api/rawfiles/upload
  body form-data file
  body form-data type 'image/jpeg'
upload text
  http://localhost:8081/api/rawfiles/upload
  body form-data file
  body form-data type 'text/plain'

when upload called, fileid returned as string

download file
  http://localhost:8081/api/rawfiles/download/<file id from db>

### helpful commands

mvn spring-boot:run -Dmaven.test.skip=true
mvn spring-boot:run -Dmaven.test.skip=true -Dspring-boot.run.jvmArguments="-Djdk.tls.client.protocols=TLSv1.2" -Dspring-boot.run.arguments="--instance.name=instanceA --server.port=8080"