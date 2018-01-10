package com.example.nttr.memo;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MemoData> list = null;
    private MyAdapter list_adapter = null;

//    // ① 変数の宣言
//    ListView listView;
//    EditText editText;  //Plan TextがEdit Textのこと
//    FloatingActionButton addButton;
//    // ListViewと表示するデータを紐付けるためのクラス
//    ArrayAdapter<String> adapter;
//    // 端末に永続的にデータを保存するためのクラス
//    SharedPreferences sharedPreferences;
//
//    List<Risuto> risuto;
//
//    // 編集の追加
//    int intEditPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_array_adapter_test);

        // 不要？
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // xmlからおつまみ名の配列を取得
        String[] item_name = this.getResources().getStringArray(R.array.memo);
        String[] item_date = this.getResources().getStringArray(R.array.cal);

        //// 日付変換←→カレンダー変換
        //// http://sauke-11.jugem.jp/?eid=63
        //Calendar cal = Calendar.getInstance();

        // 文字列←→日付変換
        // https://qiita.com/hiro2108/items/6f0b3b4682266a6b2a8c
        // http://fromasmalltown.at.webry.info/201206/article_12.html
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        this.list = new ArrayList<MemoData>(item_name.length);
        for(int i=0;i<item_name.length;i++) {
            // おつまみクラスに格納
            MemoData otsumami = new MemoData();
            otsumami.setItemName(item_name[i]);

//            try {
//                cal.setTime(sdf.parse(item_date[i]));
//            }
//            catch (Exception e) {
//                Log.d("try-catch","date to cal Parce Error.");
//            }
//            // 文字→日付→カレンダー
//            otsumami.setCalendar(cal);

            try {
                // 文字→日付
                otsumami.setDate(sdf.parse(item_date[i]));
            }
            catch (Exception e) {
                Log.d("try-catch","String to Date Parce Error:"+ item_date[i] );
            }

            this.list.add(otsumami);
        }
        this.list_adapter = new MyAdapter(this, this.list);
        // 表示
        ((ListView)findViewById(R.id.listView1)).setAdapter(this.list_adapter);


//        // ② Viewの関連付け
//        listView = findViewById(R.id.listView);
//        editText = findViewById(R.id.editText);
//        addButton = findViewById(R.id.fab);
//
//        // リスト表示される項目
//        this.risuto = new ArrayList<>();
//
//        // ③ ListViewへのAdapterのセット
//        // ListViewにAdapterをセットする
//
//        // https://qiita.com/Tsumugi/items/47f31bb7351979a45653
//        // に書き換えてみる
//
////        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
//        adapter = new ArrayAdapter<Risuto>(this, android.R.layout.simple_list_item_1, this.risuto);
//        listView.setAdapter(adapter);
//
//        // ④ SharedPreferencesを取得する
//        // MODE_PRIVATEにすると、このアプリからしかアクセスできない
//        sharedPreferences = getSharedPreferences("memo", MODE_PRIVATE);
//
//
//        // ⑤ 追加ボタンをクリックした時の処理
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // EditTextからテキストを取得します。
//                String text = editText.getText().toString();
//
//                //////////////////////////////
//                // 全消し対応、clearは特別ワードとなる
//                if (text.equals("clear")) {
//                    adapter.clear();
//                    return;
//                }
//
//                //////////////////////////////
//                // 最小を消す
//
//                //////////////////////////////
//                // 削除
//                // https://akira-watson.com/android/listview_3.html
////                adapter.remove(intEditPosition);
////
////                // ListView の更新
////                adapter.notifyDataSetChanged();
////
//                //////////////////////////////
//                // テキスト更新
//                if (intEditPosition>=0) {
//                    // リストの該当位置を書き換える
//                    adapter.getItem(intEditPosition) = text;
//                    // テキストを空にする
//                    editText.setText("");
//                    // Snackbarを使って入力された文字を表示します。
//                    Snackbar.make(view, "更新しました", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null)
//                            .show();
//
//                } else {
//
//                    // Adapterに入力したテキストを追加するようにする
//                    adapter.add(text);
//                    // テキストを空にする
//                    editText.setText("");
//                    // Snackbarを使って入力された文字を表示します。
//                    Snackbar.make(view, "追加しました", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null)
//                            .show();
//                    // ⑦ Adapterにあるデータを保存する
//                } //if intEditPosition
//                saveData();
//            }
//        });
//
//        // ⑥ ListViewのアイテムをクリックした時の処理
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//
//                // https://techbooster.org/android/ui/9039/
//                // https://developer.android.com/reference/android/widget/ArrayAdapter.html
//                String listText = adapter.getItem(position);
//                Snackbar.make(view, listText, Snackbar.LENGTH_LONG).show();
//
//                // 対象をEditTextへ取り出し、
//                // 書き換えられるようにする
//                editText.setText(listText);
//                intEditPosition = position; //　位置を保持
//
//            }
//        });
//
//        // ⑧ 保存したデータをロードしてAdapterに追加する
//        loadData();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }


    public class MemoData {

        // メンバ定義
        private String text = null;     // 文字
        private int count = 0;          // 数値

        // 日付データ（基本、現在の日時)の変換等に使用できる抽象クラス。保持には向かない
        // http://tech.pjin.jp/blog/2016/02/23/android-%E6%97%A5%E4%BB%98%E3%82%92%E6%89%B1%E3%81%86/
        //private Calendar calendar = null;
        private Date date = null;

        public String getItemName() {
            return text;
        }

        public void setItemName(String text) {
            this.text = text;
        }

        public int getCount() {
            return count;
        }

        public void addCount() {
            this.count++;
        }

        public void setCount(int num) {
            this.count += num;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        //public Calendar getCalendar() {
        //    return calendar;
        //}

        //public void setCalendar(Calendar calendar) {
        //    this.calendar = calendar;
        //}

    }

// http://www.takaiwa.net/2012/10/arrayadapterlistview.html

    private class MyAdapter extends ArrayAdapter<MemoData> {


        private LayoutInflater mInflater;

        public MyAdapter(Context context, List<MemoData> objects) {
            super(context, 0, objects);
            this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        // positionはfinalにしないと、onClick内で機能しない
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final MemoData otsumami = this.getItem(position);
            if(null == convertView) {
                // 行のレイアウトを取得（TextViewをImageButtonのレイアウト）
                convertView = this.mInflater.inflate(R.layout.list_item, null);
            }

            if(null != otsumami) {
                // おつまみ名と個数を表示
                ((TextView)convertView.findViewById(R.id.textView1)).setText(otsumami.getItemName() + " (" + otsumami.getCount() + ")");

//                // 日付変換←→カレンダー変換
//                // http://sauke-11.jugem.jp/?eid=63
//                Calendar cal = otsumami.getCalendar();

                // 文字列←→日付変換
                // https://qiita.com/hiro2108/items/6f0b3b4682266a6b2a8c
                // http://fromasmalltown.at.webry.info/201206/article_12.html
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

//                // 日付を表示
//                ((TextView)convertView.findViewById(R.id.textView2)).setText(sdf.format(cal.getTime()));

                ((TextView)convertView.findViewById(R.id.textView2)).setText(sdf.format(otsumami.getDate()));

                ((ImageButton)convertView.findViewById(R.id.imageButton1)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        // リストの中身を変更する
                        MemoData add_otumami = list.get(position);
                        add_otumami.addCount();
                        list.set(position, add_otumami);

                        // 表示の更新のやり方１：リストビューに通知をかける
                        list_adapter.notifyDataSetChanged();

                        // 表示の更新やり方2：ビューを直接いじる
//                        View parent = (View) v.getParent();
//                        ((TextView)(parent.findViewById(R.id.textView1))).setText(add_otumami.getItem_name() + " (" + add_otumami.getCount() + ")");
                    }
                });
            }
            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

//    // ⑦ メモの一覧を保存するメソッド
//    void saveData() {
//        // Setという形でデータを保存する
//        Set<String> data = new HashSet<>();
//        // for文を使って、1つずつデータを入れてく
//        for (int i = 0; i < adapter.getCount(); i++) {
//            data.add(adapter.getItem(i));
//        }
//        // putStringSetを使ってデータを一覧で保存する
//        sharedPreferences.edit().putStringSet("data", data).apply();
//    }
//
//    // ⑧ 保存してあるメモの一覧を取得するメソッド
//    void loadData() {
//        // Setという形でデータを保存したので、Setで取得する
//        Set<String> data = sharedPreferences.getStringSet("data", new HashSet<String>());
//        // adapterには、addAllという関数で配列や、Setなどのデータの集まりを渡すことができる
//        adapter.addAll(data);
//    }

}
