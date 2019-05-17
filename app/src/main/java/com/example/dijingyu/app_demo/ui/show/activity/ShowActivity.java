package com.example.dijingyu.app_demo.ui.show.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dijingyu.app_demo.R;
import com.example.dijingyu.app_demo.base.BaseActivity;
import com.example.dijingyu.app_demo.base.Constants;
import com.example.dijingyu.app_demo.bean.PersonageInfoBean;
import com.example.dijingyu.app_demo.bean.VersionBean;
import com.example.dijingyu.app_demo.mvp.presenter.EmptyP;
import com.example.dijingyu.app_demo.mvp.presenter.PersonageInfoP;
import com.example.dijingyu.app_demo.mvp.view.PersonageInfoV;
import com.example.dijingyu.app_demo.ui.VpAdapter;
import com.example.dijingyu.app_demo.ui.personage.activity.PersonageShowActivity;
import com.example.dijingyu.app_demo.ui.show.fragment.BaiDuMapFragment;
import com.example.dijingyu.app_demo.ui.show.fragment.BanMiFragment;
import com.example.dijingyu.app_demo.ui.show.fragment.HomeFragment;
import com.example.dijingyu.app_demo.ui.show.fragment.MyFragment;
import com.example.dijingyu.app_demo.utils.InstallUtil;
import com.example.dijingyu.app_demo.utils.SDUtils;
import com.example.dijingyu.app_demo.utils.SpUtil;
import com.example.dijingyu.app_demo.utils.ToastUtil;
import com.example.dijingyu.app_demo.utils.Tools;
import com.example.dijingyu.app_demo.widget.BanViewPager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ShowActivity extends BaseActivity<PersonageInfoP, PersonageInfoV> implements PersonageInfoV {


    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitle;
    private String mToken;
    private TabLayout mTab;
    private BanViewPager mVp;

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        mPresenter.getPersonageInfo(mToken);
        mTab = findViewById(R.id.tab);
        mVp = findViewById(R.id.vp);
        initData();
        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), mFragments, mTitle);
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);
        mTab.getTabAt(0).setIcon(R.drawable.tab_home_top);
        mTab.getTabAt(1).setIcon(R.drawable.tab_home_top);
        mTab.getTabAt(2).setIcon(R.drawable.tab_banmi_top);
        mTab.getTabAt(3).setIcon(R.drawable.tab_banmi_top);
    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new BaiDuMapFragment());
        mFragments.add(new BanMiFragment());
        mFragments.add(new MyFragment());
        mTitle = new ArrayList<>();
        mTitle.add("首页");
        mTitle.add("发现");
        mTitle.add("伴米");
        mTitle.add("我的");
    }


    @Override
    protected PersonageInfoP initPresenter() {
        return new PersonageInfoP();
    }


    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_show;
    }

    public static void startAct(Context context) {
        Intent intent = new Intent(context, ShowActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof VersionBean){
            VersionBean versionBean = (VersionBean) o;
            String sdPath = SDUtils.getSDPath();
            String version = versionBean.getResult().getInfo().getVersion();
            String versionName = Tools.getVersionName();
            final String fileName = "banmi.apk";
            boolean b = fileIsExists(sdPath + "/" + fileName);
            if (b){
//                install(sdPath + "/" + fileName);
//                InstallUtil.installApk(this,sdPath + "/" + fileName);
            }else {
                ToastUtil.showShort("开始下载");
                downLoadFile(versionBean.getResult().getInfo().getDownload_url());

            }
        }

    }

    private void install(String filePath) {
        Log.i("123456", "开始执行安装: " + filePath);
        File apkFile = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.w("123456", "版本大于 N ，开始使用 fileProvider 进行安装");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(
                    this
                    , "com.example.dijingyu.app_demo.fileprovider"
                    , apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            Log.w("123456", "正常进行安装");
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }
    @Override
    public void onFail(String tips) {

    }

    @Override
    public void toastShort(String string) {

    }
    //判断文件是否存在
    public boolean fileIsExists(String strFile) {
        try {
            File f=new File(strFile);
            if(!f.exists()) {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }


    //文件下载
    private void downLoadFile(final String httpUrl) {
        // TODO Auto-generated method stub
        new Thread(new Runnable() {
            @Override
            public void run() {
                String sdPath = SDUtils.getSDPath();
                final String fileName = "banmi.apk";
                final File file = new File(sdPath+"/" + fileName);
                try {
                    URL url = new URL(httpUrl);
                    try {
                        HttpURLConnection conn = (HttpURLConnection) url
                                .openConnection();
                        InputStream is = conn.getInputStream();
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] buf = new byte[256];
                        conn.connect();
                        double count = 0;
                        if (conn.getResponseCode() >= 400) {
                            Toast.makeText(ShowActivity.this, "连接超时", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            while (count <= 100) {
                                if (is != null) {
                                    int numRead = is.read(buf);
                                    if (numRead <= 0) {
                                        break;
                                    } else {
                                        fos.write(buf, 0, numRead);
                                    }

                                } else {
                                    break;
                                }

                            }
                        }

                        conn.disconnect();
                        fos.close();
                        is.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block

                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();
                }
//                install(sdPath + "/" + fileName);
//                InstallUtil.installApk(ShowActivity.this,sdPath + "/" + fileName);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort("下载完成，请手动安装...");
                    }
                });
            }
        }).start();
    }

}
