package quan.common.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import quan.common.exceptions.ApiException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class CustomErrorDecoder implements ErrorDecoder {

    // Method to read a string from an InputStream
    private String getStringFromStream(InputStream stream) throws IOException {
        // Create a BufferedInputStream to read from the stream
        BufferedInputStream bis = new BufferedInputStream(stream);
        // Create a ByteArrayOutputStream to store the data
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        // Read data from the stream and write it to the buffer
        for (int result = bis.read(); result != -1; result = bis.read()) {
            buf.write((byte) result);
        }
        // Return the data as a string
        return buf.toString(StandardCharsets.UTF_8);
    }

    // Method to decode a Response object
    @Override
    public Exception decode(String methodKey, Response response) {
        String message;
        // Get the body of the response
        Response.Body responseBody = response.body();
        try {
            // Read the body as a string
            message = getStringFromStream(responseBody.asInputStream());
        } catch (IOException e) {
            // Throw a runtime exception if an error occurs
            throw new RuntimeException(e);
        }
        // Create and return a new RestApiException with the message
        return new ApiException(message);
    }
}
/*
Phương thức getStringFromStream nhận một đối tượng
InputStream và trả về một chuỗi được đọc từ luồng đó.
Nó sử dụng các lớp BufferedInputStream và ByteArrayOutputStream
để đọc dữ liệu từ luồng và lưu trữ nó trong bộ nhớ.
Phương thức decode ghi đè phương thức của lớp cha và được
sử dụng để giải mã một đối tượng Response. Nó lấy ra nội
dung của phản hồi bằng cách gọi phương thức
getStringFromStream với đối tượng InputStream được truy xuất
từ phản hồi. Sau đó, nó tạo và trả về một đối tượng
RestApiException mới với thông điệp lấy từ nội dung phản hồi.
 */
