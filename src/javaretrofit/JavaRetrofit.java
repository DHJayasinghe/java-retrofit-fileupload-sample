/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaretrofit;

import com.sun.jndi.toolkit.url.Uri;
import java.io.File;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javaretrofit.net.RetrofitClient;
import javaretrofit.net.Services;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javaretrofit.net.GlobalResponse;

/**
 *
 * @author Ezequiel
 */
public class JavaRetrofit {
    List<MultipartBody.Part> parts = new ArrayList<>();
        
     File mCurrentPhotoPath = new File("C:\\Users\\USER\\Desktop\\Java_Retrofit-master\\src\\Image\\image.jpg");
     String path = "C:\\Users\\USER\\Desktop\\Java_Retrofit-master\\src\\Image\\image.jpg";
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

       JavaRetrofit aa = new JavaRetrofit();
       aa.upload();
        
     
    }
    
    
    private MultipartBody.Part prepareFilePart(String partName, String fileUri){
        File file = new File(fileUri.toString());
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        //create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse(mimeType), file);
        //MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
    
     public void addtoArray(){
        parts.add(prepareFilePart("photo",mCurrentPhotoPath.getAbsolutePath()));
    }
     
     public void upload(){
         Services api = RetrofitClient.getAPIService();
         addtoArray();
         
         api.sendImageTest(parts,1,"sampath").enqueue(new Callback<GlobalResponse>() {
             @Override
             public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> rspns) {
                     rspns.body();
                    int a=0; 
                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }

             @Override
             public void onFailure(Call<GlobalResponse> call, Throwable thrwbl) {
                
                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
             
         });

     
     }
    
    
    
    
    
    
    
}
