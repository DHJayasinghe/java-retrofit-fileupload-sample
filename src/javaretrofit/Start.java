package javaretrofit;

public class Start {
    
    public static void main(String[] args) {
        JavaRetrofit fileupload = new JavaRetrofit();
        String filepath = "C:\\Users\\Dhanuka Jayasinghe\\Downloads\\shipment_registry_before.png";
        // fileupload.uploadUsingSimpleModel(filepath);
        // fileupload.uploadUsingSimpleModel2(filepath);
        fileupload.uploadUsingComplexModel(filepath);
    }
}
