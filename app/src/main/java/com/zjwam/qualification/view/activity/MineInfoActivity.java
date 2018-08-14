package com.zjwam.qualification.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.zjwam.qualification.R;
import com.zjwam.qualification.basic.BaseActivity;
import com.zjwam.qualification.bean.MineInfoBean;
import com.zjwam.qualification.custom.ChoicePictureDialog;
import com.zjwam.qualification.presenter.MineInfoPresenter;
import com.zjwam.qualification.presenter.ipresenter.IMineInfoPresenter;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.utils.RequestOptionsUtils;
import com.zjwam.qualification.view.iview.IMineInfoView;

import java.io.File;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class MineInfoActivity extends BaseActivity implements IMineInfoView {

    private ImageView back,mine_info_tx;
    private TextView title,mine_info_loginName,mine_info_nickName;
    private EditText mine_info_nick2Name,mine_info_qm,mine_info_mail;
    private Spinner mine_info_sex;
    private Button mine_info_up;
    private ChoicePictureDialog choicePictureDialog;
    private Uri imageUri;
    private static final int CAMERA = 1;
    private static final int CHOOSE_PICTURE = 2;
    private static final int CROP_SMALL_PICTURE = 3;
    private String photo_path = null;
    private IMineInfoPresenter mineInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info);
        initView();
        initData();
    }

    private void initData() {
        title.setText("个人信息");
        mineInfoPresenter = new MineInfoPresenter(this,this);
        String[] arr={"男","女","保密"};
        //创建ArrayAdapter对象
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mine_info_sex.setAdapter(adapter);

        choicePictureDialog = new ChoicePictureDialog(MineInfoActivity.this);
        choicePictureDialog.setOnClickListener(new ChoicePictureDialog.PopOnClick() {
            @Override
            public void OnClickPhotograph() {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg"));
                //将拍照所得的相片保存到SD卡根目录
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, CAMERA);
                choicePictureDialog.dismiss();
            }
            @Override
            public void OnClickalbum() {
                Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, CHOOSE_PICTURE);
                choicePictureDialog.dismiss();
            }

            @Override
            public void OnClickcancel() {
                choicePictureDialog.dismiss();
            }
        });

        back.setOnClickListener(onClickListener);
        mine_info_tx.setOnClickListener(onClickListener);
        mine_info_up.setOnClickListener(onClickListener);
        mineInfoPresenter.getInfo();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.mine_info_tx:
                    choicePictureDialog.show();
                    break;
                case R.id.mine_info_up:
                    mineInfoPresenter.upInfo(mine_info_nick2Name.getText().toString(),mine_info_qm.getText().toString(),mine_info_sex.getSelectedItem().toString(),mine_info_mail.getText().toString());
                    break;
            }
        }
    };
    private void initView() {
        back = findViewById(R.id.back);
        mine_info_tx = findViewById(R.id.mine_info_tx);
        title = findViewById(R.id.title_name);
        mine_info_loginName = findViewById(R.id.mine_info_loginName);
        mine_info_nick2Name = findViewById(R.id.mine_info_nick2Name);
        mine_info_qm = findViewById(R.id.mine_info_qm);
        mine_info_mail = findViewById(R.id.mine_info_mail);
        mine_info_sex = findViewById(R.id.mine_info_sex);
        mine_info_up = findViewById(R.id.mine_info_up);
        mine_info_nickName = findViewById(R.id.mine_info_nickName);
    }

    /**
     * 不裁剪直接设置用这个方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            //选择照片
//            case CHOOSE_PICTURE:
//                if (data != null) {
//                    imageUri = data.getData();
//                    photo_path = uriToFile(imageUri, this);
//                }
//                break;
//            case CAMERA:
//                if (resultCode == Activity.RESULT_OK) {
//                    photo_path = imageUri.getPath();
//                }
//                break;
//        }
//        if (photo_path != null) {
//            //图片处理
//            GlideImageUtil.setImageView(getBaseContext(),photo_path,add_company_image, RequestOptionsUtils.commonTransform());
//            upImg(Config.URL + "api/info/company_img", ZkwPreference.getInstance(getBaseContext()).getUid(),photo_path);
//        }
//    }

    /**
     * 图片进行裁剪之后进行设置
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //选择照片
            case CHOOSE_PICTURE:
                if (data != null) {
                    imageUri = data.getData();
                    cropPhoto(imageUri);
                }
                break;
            case CAMERA:
                if (resultCode == Activity.RESULT_OK) {//判断是否拍照成功(点击了返回键即不进入方法)
                    cropPhoto(imageUri);
                }
                break;
            case CROP_SMALL_PICTURE:
                if (data != null) {
                    setImageUri(data);
                    photo_path = uriToFile(imageUri, this);
                    //如果要上传到服务器，在这里调取接口即可
                    mineInfoPresenter.upImg(photo_path);
                }
                break;
        }
    }


    /**
     * 系统的裁剪功能
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 得到裁剪之后的图片uri
     *
     * @param intent
     */
    private void setImageUri(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("data");
            imageUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));//bitmap转为uri
            //Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);（uri转为bitmap）
        }
    }

    /**
     * uri转为file
     *
     * @param uri
     * @param context
     * @return
     */
    public static String uriToFile(Uri uri, Context context) {
        String path = null;
        switch (uri.getScheme()) {
            case "content":
                Cursor cursor = context.getContentResolver().query(uri,
                        new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (cursor != null) {
                    int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    path = cursor.getString(index);
                    cursor.close();
                }
                break;
            default:
                path = uri.getPath();
        }
        return path;
    }

    @Override
    public void setTxSuccess() {
        GlideImageUtil.setImageView(getBaseContext(),photo_path,mine_info_tx, RequestOptionsUtils.circleTransform());
        mineInfoPresenter.setRefresh(true);
    }

    @Override
    public void showMsg(String msg) {
        error(msg);
    }

//    mine_info_tx;
//    private TextView title,mine_info_loginName;
//    private EditText mine_info_nick2Name,mine_info_qm,mine_info_mail;
    @Override
    public void setMineInfo(MineInfoBean mineInfoBean) {
        GlideImageUtil.setImageView(getBaseContext(),mineInfoBean.getPic(),mine_info_tx, RequestOptionsUtils.circleTransform());
        mine_info_loginName.setText(mineInfoBean.getUsername());
        mine_info_nickName.setText(mineInfoBean.getNickname());
        mine_info_nick2Name.setText(mineInfoBean.getNickname());
        mine_info_qm.setText(mineInfoBean.getSign());
        mine_info_mail.setText(mineInfoBean.getEmail());
        if ("男".equals(mineInfoBean.getSex())){
            mine_info_sex.setSelection(0);
        }else if ("女".equals(mineInfoBean.getSex())){
            mine_info_sex.setSelection(1);
        }else {
            mine_info_sex.setSelection(2);
        }
    }

    @Override
    public void upInfoSuccess() {
        mineInfoPresenter.setRefresh(true);
        finish();
    }
}
