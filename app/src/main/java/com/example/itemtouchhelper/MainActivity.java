package com.example.itemtouchhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private List<String> dataset = new ArrayList<>();
    private List<String> detailset = new ArrayList<>();
    private FloatingActionButton fButton;
    private FragmentManager fragmentManager;
    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fButton = findViewById(R.id.fab);
        //1. RecyclerViewの作成
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);
        //2.LayoutManagerの作成
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //3.RecyclerViewにLayoutManagerをセット
        recyclerView.setLayoutManager(layoutManager);

        //RecyclerView.Adapterを継承したクラスのインスタンスを作成, 引数に扱うデータ?
        adapter = new MyAdapter(dataset, detailset) {
            @Override
            protected void onItemClick(View v, int position, String itemData, String detail) {
                fragmentManager = getSupportFragmentManager();
                dialogFragment = ItemDialog.newInstance(itemData, detail, position);
                dialogFragment.show(fragmentManager, "item");
            }
        };
        recyclerView.setAdapter(adapter);

        //スワイプ処理等
        ItemTouchHelper mIth = new ItemTouchHelper(
                new SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos   = target.getAdapterPosition();
                        adapter.notifyItemMoved(fromPos, toPos);
                        return true;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        dataset.remove(fromPos);
                        detailset.remove(fromPos);
                        adapter.notifyItemRemoved(fromPos);
                    }
                });

        mIth.attachToRecyclerView(recyclerView);


        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                dialogFragment = new MyAlert();
                dialogFragment.show(fragmentManager, "test");
            }
        });
    }
    @NonNull
    public void addTitle(@NonNull String title,@NonNull String detail) {
        dataset.add(title);
        detailset.add(detail);
        adapter.notifyDataSetChanged();
    }
    public void changeTitle(String title, String detail, int position) {
        dataset.set(position, title);
        detailset.set(position, detail);
        adapter.notifyItemChanged(position);
    }

}
