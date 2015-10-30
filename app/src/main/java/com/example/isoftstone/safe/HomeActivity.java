package com.example.isoftstone.safe;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.MD5Utils;

public class HomeActivity extends AppCompatActivity {

    private GridView gvView;
    private int[] mPics = new int[] { R.drawable.home_safe,
            R.drawable.home_callmsgsafe, R.drawable.home_apps,
            R.drawable.home_taskmanager, R.drawable.home_netmanager,
            R.drawable.home_trojan, R.drawable.home_sysoptimize,
            R.drawable.home_tools, R.drawable.home_settings };
    private SharedPreferences mpref;
    private String[] mItems = new String[] { "手机防盗", "通讯卫士", "软件管理", "进程管理",
            "流量统计", "手机杀毒", "缓存清理", "高级工具", "设置中心" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mpref = getSharedPreferences("config",MODE_PRIVATE);

        gvView = (GridView)findViewById(R.id.gv_home);
        gvView.setAdapter(new HomeAdapter());
        gvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        showPassWordDialog();
                        break;
                        default:
                            break;
                }
            }
        });
    }

    private void showPassWordDialog() {
        String savePassword = mpref.getString("password",null);
        if (!TextUtils.isEmpty(savePassword)){
            showPasswordInputDialog();
        }else {
            showPasswordSetDialog();
        }
    }

    private void showPasswordSetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog =  builder.create();
        View view = View.inflate(this,R.layout.dialog_set_password,null);
        dialog.setView(view,0,0,0,0);
        final EditText etPassword = (EditText)view.findViewById(R.id.et_password);
        final EditText etPassWordConfirm = (EditText)view.findViewById(R.id.comfirm_password);

        Button btnOK = (Button)view.findViewById(R.id.btn_ok);
        Button butCancel = (Button)view.findViewById(R.id.btn_cancle);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                String confirmPassWord = etPassWordConfirm.getText().toString();
                if (!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(confirmPassWord)){
                    if (password.equals(confirmPassWord)){
                        mpref.edit().putString("password", MD5Utils.encode(password)).commit();
                        dialog.dismiss();
                        startActivity(new Intent(HomeActivity.this,LostFindActivity.class));

                    }else {
                        Toast.makeText(HomeActivity.this,"两次密码不匹配",Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(HomeActivity.this,"密码输入不能为空",Toast.LENGTH_SHORT).show();

                }
            }
        });
        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showPasswordInputDialog() {
        AlertDialog.Builder builder = new  AlertDialog.Builder(this);
        final AlertDialog alertDialog =  builder.create();
        View view = View.inflate(this, R.layout.dialog_password_input, null);
        alertDialog.setView(view);
        final EditText password = (EditText)view.findViewById(R.id.input_password);

        Button buttonOK = (Button)view.findViewById(R.id.btn_ok);
        Button buttonCancle = (Button)view.findViewById(R.id.btn_cancle);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputPassword = password.getText().toString();
                String mfpassword = mpref.getString("password",null);
                if (!TextUtils.isEmpty(inputPassword)){
                    if (MD5Utils.encode(inputPassword).equals(mfpassword)){
                        alertDialog.dismiss();
                        startActivity(new Intent(HomeActivity.this,LostFindActivity.class));

                    }else {
                        Toast.makeText(HomeActivity.this,"密码输入错误",Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(HomeActivity.this,"密码输入不能为空",Toast.LENGTH_SHORT).show();

                }
            }
        });
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    class HomeAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            return mItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(HomeActivity.this,R.layout.home_list_item,null);
            ImageView imageView = (ImageView)view.findViewById(R.id.iv_item);
            TextView textView = (TextView)view.findViewById(R.id.tv_item);

            textView.setText(mItems[position]);
            imageView.setImageResource(mPics[position]);
            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
