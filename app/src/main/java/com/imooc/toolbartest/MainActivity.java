package com.imooc.toolbartest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private ShareActionProvider mShareActionProvider;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);
        String[] strs = new String[]{"zhangsan","lisi","wangwu","wangermazi"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strs);
        list.setAdapter(arrayAdapter);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setLogo(R.drawable.ic_launcher);
        // 标题的文字需在setSupportActionBar之前，不然会无效
        mToolbar.setTitle("主标题打开状态");
        mToolbar.setSubtitle("副标题打开状态");

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_launcher);

      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);// 给左上角图标的左边加上一个返回的图标  在代码中加上去掉没影响
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout,mToolbar,
                R.string.abc_action_bar_home_description,
                R.string.abc_action_bar_home_description_format){
            @Override
            public void onDrawerClosed(View drawerView) {
                mToolbar.setTitle("主标题关闭状态");
                mToolbar.setSubtitle("副标题关闭状态");
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                mToolbar.setTitle("主标题打开状态");
                mToolbar.setSubtitle("副标题打开状态");
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerToggle.syncState();//指示ActionBarDrawerToggle与DrawerLayout的状态同步，并将ActionBarDrawerToggle中的drawer图标，设置为ActionBar的Home-Button的icon
        mDrawerLayout.setDrawerListener(mDrawerToggle);

		// 菜单的监听可以在toolbar里设置，
		// 也可通过Activity的onOptionsItemSelected回调方法来处理
        mToolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Toast.makeText(MainActivity.this,
                                "action_settings", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this,
                        "action_settings", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }


        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
		/* ShareActionProvider配置 */
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu
                .findItem(R.id.action_share));
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        mShareActionProvider.setShareIntent(intent);
        return super.onCreateOptionsMenu(menu);
    }




}
