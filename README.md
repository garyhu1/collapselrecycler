# collapselrecycler
仿通讯录的recyclerView+可折叠的图片浏览，仿饿了么

# (一)、使用要点
# A概述
# 1.添加依赖compile 'com.android.support:design:23.1.1'
# 1.CollaspingToolbarLayout必须放在CoordinatorLayout中才能生效
# 2.CoordinatorLayout中整体上分为两部分，第一部分为AppBarLayout，AppBarLayout中包裹一个CollapsingToolbarLayout，CollapsingToolbarLayout中有两个控件，一个是头部显示的ImageView，另一个是Toolbar；第二部分为RecyclerView或者NestedScrollView。
# B CollapsingToolbarLayout属性详解
1. app:layout_scrollFlags="" 该属性表示CollapsingToolbarLayout的滚动标签，共有5个取值：
i.   scroll 表示CollapsingToolbarLayout可以滚动
ii.  enterAlways 表示底部的ScrollView只要向下滚动，头部就显示出来
iii.  enterAlwaysCollapsed 表示当ScrollView的滚动见顶时，头部显示出来
iv.  exitUntilCollapsed 表示头部折叠到最小高度时（Toolbar的高度），就不再折叠
v.  snap 表示在滑动过程中如果停止滑动，则头部会就近折叠
2. app:contentScrim="@color/colorPrimary" 表示当头部折叠完成时显示的颜色
3.app:layout_collapseMode=""表示控件的折叠模式，有两种取值：
i.   parallax 表示控件的折叠和CollapsingToolbarLayout的折叠不同步
ii.   pin 表示折叠完成时将该控件停放在顶部
4. app:laouy_collapseParallaxMultiplier="" 视觉乘数（设置视觉差，取值0-1，数值越大，视觉差越大）
5. app:title="Title" 属性会覆盖Toolbar中的title（推荐将Title写在这里）
6. app:expandedTitleMarginXXXXX  设置Title的边距
7. app:collapsedTitleGravity="right" 折叠之后文本的位置
8.app:expandedTitleGravity="right|bottom" 展开之后文本的位置

# 添加了权限的管理
# banner和下拉刷新的冲突解决
# RecyclerView的下拉刷新和上拉加载
# RecyclerView的侧滑删除
# 城市的选择


##and.utils.system_hardware_software_receiver_shell.software.wifi
| 名字 | 功能  | 优点|
| :------------: |:---------------:| :-----:|
| NetManager  | 查看当前网络类型与是否联网 |  |
| MyWifiAnd3G  | 查看wifi列表，关闭/打开wifi/3G,startScans,获取ipAddress等 |  |
| NetStatusReceiver  | `查看网络类型,与关于网络广播的类` |  |
| NetworkManagerFinal  | 别人的留着 有些bug碰到或许可以参考下 |  |

##SmsObserver
短信自动填写的帮助类
###具体使用
SmsObserver smsObserver = new SmsObserver(this, new Handler(),
                new SmsObserver.SmsListener() {
                    @Override
                    public void onResult(String smsContent) {
                        //todo
                        loginVerify.setText(smsContent);
                    }
                });
this.getContentResolver().registerContentObserver(
                Uri.parse("content://sms/"), true, smsObserver);
