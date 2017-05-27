package com.xiarh.purenews.ui.weather;

import android.Manifest;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseFragment;
import com.xiarh.purenews.bean.WeatherBean;
import com.xiarh.purenews.config.Config;
import com.xiarh.purenews.util.SnackBarUtil;
import com.xiarh.purenews.util.WeatherUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 天气
 * Created by xiarh on 2017/5/24.
 */

public class WeatherFragment extends BaseFragment implements AMapLocationListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_weather)
    TextView tvWeather;
    @BindView(R.id.img_weather)
    ImageView imgWeather;
    @BindView(R.id.tv_aqi)
    TextView tvAQI;

    private String mCity;

    private boolean hasMsg = false; //true：已经有天气信息 false：还没有天气信息

    private AMapLocationClient mLocationClient = null;

    private AMapLocationClientOption mLocationOption = null;

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            if (requestCode == 200) {
                if (!hasMsg) {
                    startLoaction();
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            if (requestCode == 200) {

            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.frg_weather;
    }

    @Override
    protected void init() {
        getPermission();
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.black));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setEnabled(false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getPermission();
        }
    }

    /**
     * 获取权限
     */
    private void getPermission() {
        AndPermission
                .with(this)
                .permission(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .requestCode(200)
                .callback(mPermissionListener)
                .start();
    }

//     AlertDialog.newBuilder(getActivity())
//            .setTitle("友好提醒")
//                .setMessage("请允许此应用定位权限")
//                .setPositiveButton("好，给你", new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            rationale.resume();
//        }
//    })
//            .setNegativeButton("我拒绝", new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            rationale.cancel();
//        }
//    }).show();

    /**
     * 开始定位
     */
    private void startLoaction() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔 单位毫秒
        mLocationOption.setInterval(100 * 1000 * 60 * 60);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        //显示定位中
        SnackBarUtil.showSnackBar(R.string.locating, mSwipeRefreshLayout, getActivity());
    }

    /**
     * 销毁定位
     */
    private void destoryLocation() {
        if (null != mLocationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            mLocationClient.onDestroy();
            mLocationClient = null;
            mLocationOption = null;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                mCity = aMapLocation.getCity();
                mSwipeRefreshLayout.setEnabled(true);
                mSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }
                });
                getWeatherData(mCity);
            }
            //定位失败，通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息
            else {
                SnackBarUtil.showSnackBar(aMapLocation.getErrorInfo(), mSwipeRefreshLayout, getActivity());
            }
        }
    }

    /**
     * 获取天气信息
     */
    private void getWeatherData(String city) {
        OkGo.get(Config.getWeatherUrl(city))
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        WeatherBean bean = gson.fromJson(s, WeatherBean.class);
                        setWeatherView(bean);
                        if (null != mSwipeRefreshLayout) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                        hasMsg = true;
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        SnackBarUtil.showSnackBar(e.getMessage(), mSwipeRefreshLayout, getActivity());
                    }
                });
    }

    private void setWeatherView(WeatherBean bean) {
        WeatherBean.HeWeather5Bean weather5Bean = bean.getHeWeather5().get(0);
        tvWeather.setText(weather5Bean.getNow().getTmp() + "°C");
        tvCity.setText(weather5Bean.getBasic().getCity());
        tvAQI.setText("AQI " + weather5Bean.getAqi().getCity().getAqi() + "(" + weather5Bean.getAqi().getCity().getQlty() + ")");
        WeatherUtil.setWeatherIcon(weather5Bean.getNow().getCond().getCode(), imgWeather);
    }

    @Override
    public void onRefresh() {
        if (!mCity.isEmpty()) {
            getWeatherData(mCity);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destoryLocation();
    }
}
