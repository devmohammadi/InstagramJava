package com.fmohammadi.instagramjava.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fmohammadi.instagramjava.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.hendraanggrian.appcompat.widget.SocialAutoCompleteTextView;
import com.theartofdev.edmodo.cropper.CropImage;

public class PostActivity extends AppCompatActivity {

    private Uri imageUri;
    private String imageUrl;

    private ImageView close;
    private ImageView image_added;
    private TextView post;
    private SocialAutoCompleteTextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        close = findViewById(R.id.close);
        image_added = findViewById(R.id.imahe_added);
        post = findViewById(R.id.post);
        description = findViewById(R.id.description);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostActivity.this , MainActivity.class));
                finish();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }

            private void upload() {
                ProgressDialog progressDialog = new ProgressDialog(PostActivity.this);
                progressDialog.setMessage("Uploading...");
                progressDialog.show();

                if(imageUri != null){
                    final StorageReference filePath = FirebaseStorage.getInstance().getReference("Posts")
                            .child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
                    StorageTask uploadTask = filePath.getFile(imageUri);
                    uploadTask.continueWithTask(new Continuation() {
                        @Override
                        public Object then(@NonNull Task task) throws Exception {
                           if (task.isSuccessful()){
                               throw task.getException();
                           }
                           return filePath.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task <Uri> task) {
                            Uri downloadUri = task.getResult();
                            imageUrl = downloadUri.toString();

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
                        }
                    });

                }
            }

            private String getFileExtension(Uri uri) {
                return MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(uri));
            }
        });

        CropImage.activity().start(PostActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && requestCode == RESULT_OK){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();
            image_added.setImageURI(imageUri);
        }else {
            Toast.makeText(PostActivity.this , "Try Again!!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(PostActivity.this , MainActivity.class));
            finish();
        }
    }
}