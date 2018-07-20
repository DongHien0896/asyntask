package com.example.dong.demoasynctask;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerImage;
    private List<Image> mImages;
    private ImageAdapter mImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerImage = findViewById(R.id.recycler_image);
        mImages = new ArrayList<>();
        checkPermission();
    }

    private void checkPermission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        Constants.REQUESTCODE_PERMISSION);
            }
        } else {
            setComponent();
        }
    }

    private void setComponent() {
        mRecyclerImage.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
        mImageAdapter = new ImageAdapter(this, mImages);
        mRecyclerImage.setAdapter(mImageAdapter);
        new AsyncTaskGetImage(mInterfLoadImage).execute();
    }

    private InterfLoadImage mInterfLoadImage = new InterfLoadImage() {
        @Override
        public void loadImage(List<Image> listImage) {
            mImageAdapter.setImageList(listImage);
            mImageAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Constants.REQUESTCODE_PERMISSION: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        setComponent();
                    } else {
                        Toast.makeText(MainActivity.this, Constants.MESSAGE_ERRO, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

}