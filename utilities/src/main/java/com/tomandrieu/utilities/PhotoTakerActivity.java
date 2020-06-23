package com.tomandrieu.utilities;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;
import static com.tomandrieu.utilities.constants.ImageConstants.AUTHORITIES;

public abstract class PhotoTakerActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    public static final int RC_TAKE_PHOTO = 100;
    private static final String[] PERMS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public boolean isAskPermissionOnTakingSinglePhoto;

    protected String mCurrentPhotoPath;
    protected Uri photoURI;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 2 - Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (isAskPermissionOnTakingSinglePhoto) {
            startTakingSinglePhoto(null);
            isAskPermissionOnTakingSinglePhoto = false;
        }
    }

    public void startTakingSinglePhoto(View view) {
        if (!EasyPermissions.hasPermissions(getApplicationContext(), PERMS)) {
            isAskPermissionOnTakingSinglePhoto = true;
            EasyPermissions.requestPermissions(this, getResources().getString(R.string.ask_permission_files_access), RC_TAKE_PHOTO, PERMS);
            return;
        }

        Intent i = getPickImageChooserIntent();
        if (i != null) {
            startActivityForResult(i, RC_TAKE_PHOTO);
        } else {
            Toast.makeText(this, "can't access camera app", Toast.LENGTH_SHORT).show();
        }
    }

    public Intent getPickImageChooserIntent() {

        List<Intent> allIntents = new ArrayList();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            File photoFile;
            photoFile = createImageFile();
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, AUTHORITIES, photoFile);
                Log.e("=>", photoURI.toString() + " ");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
            } else {
                return null;
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, getResources().getString(R.string.choose_photo_taker));

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    /**
     * Get URI to image received from capture by camera.
     */
    private File createImageFile() {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        String imageFileName = "RideMonPark_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (!storageDir.exists()) {
            if (!storageDir.mkdirs()) {
                return null;
            }
        }
        File image = new File(storageDir, imageFileName + ".jpg");

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
