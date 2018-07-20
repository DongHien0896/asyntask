package com.example.dong.demoasynctask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AsyncTaskGetImage extends android.os.AsyncTask<Void, Void, List<Image>> {

    private InterfLoadImage mInterfLoadImage;

    public AsyncTaskGetImage(InterfLoadImage interfLoadImage) {
        this.mInterfLoadImage = interfLoadImage;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Image> doInBackground(Void... voids) {
        List<Image> listImage = new ArrayList<>();
        File folder = new File(Constants.FOLDER);
        new GetImage().getAllImages(folder, listImage);
        return listImage;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Image> itemImages) {
        super.onPostExecute(itemImages);
        mInterfLoadImage.loadImage(itemImages);
        this.cancel(true);
    }
}
