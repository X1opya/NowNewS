package com.x1opya.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

public class MyStackAdapter extends StackAdapter<News>  {
    public static Integer[] TEST_DATAS = new Integer[]{
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
            R.color.color_6,
            R.color.color_7,
            R.color.color_8,
            R.color.color_9,
            R.color.color_10,
            R.color.color_11,
            R.color.color_12,
            R.color.color_13,
            R.color.color_14,
            R.color.color_15,
            R.color.color_16,
            R.color.color_17,
            R.color.color_18,
            R.color.color_19,
            R.color.color_20,
            R.color.color_21,
            R.color.color_22,
            R.color.color_23,
            R.color.color_24,
            R.color.color_25,
            R.color.color_26
    };



    public MyStackAdapter(Context context) {
        super(context);
    }

    @Override
    public void bindView(News data, int position, CardStackView.ViewHolder holder) {
        MyViewHolder h = (MyViewHolder) holder;
        h.onBind(data);

    }

    @Override
    protected MyViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.post_item,parent,false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends CardStackView.ViewHolder {

        View mLayout;
        View mContainerContent;

        TextView title, time, resource, description, link;
        ImageView img;
        public MyViewHolder(View view) {
            super(view);

            mLayout = view.findViewById(R.id.frame_list_card_item);
            mContainerContent = view.findViewById(R.id.container_list_content);

            title = view.findViewById(R.id.title);
            time = view.findViewById(R.id.time);
            resource = view.findViewById(R.id.resource);
            description = view.findViewById(R.id.description);
            link = view.findViewById(R.id.link);
            img = view.findViewById(R.id.img);

        }

        @Override
        public void onItemExpand(boolean b) {
            mContainerContent.setVisibility(b ? View.VISIBLE : View.GONE);
        }

        @Override
        protected void onAnimationStateChange(int state, boolean willBeSelect) {
            super.onAnimationStateChange(state, willBeSelect);
            if(state == CardStackView.ANIMATION_STATE_START && willBeSelect) onItemExpand(true);
            if(state == CardStackView.ANIMATION_STATE_END && !willBeSelect) onItemExpand(false);
        }

        public  void onBind(final News news){
            //link.setText(news.url);
            description.setText(news.getbodyNews());
            title.setText(news.title);
            resource.setText(news.source.name);
            time.setText(news.getTime());
            Random r = new Random();
            Drawable drawable = img.getDrawable();
            GradientDrawable bgShape = (GradientDrawable)img.getBackground();
            bgShape.setColor(getContext().getResources().getColor(TEST_DATAS[r.nextInt(25)]));
            //shapeDrawable.setColor(TEST_DATAS[r.nextInt(26)]);
            //img.setBackgroundColor(itemView.getResources().getColor(TEST_DATAS[r.nextInt(26)]));
            //img.getBackground().setColorFilter(getContext().getDrawable(TEST_DATAS[r.nextInt(26)]),PorterDuff.Mode.SRC_OVER);
            //img.setVisibility(View.GONE);

            //mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), r.nextInt(25)), PorterDuff.Mode.SRC_IN);
            if(news.urlToImage != null) {
                Picasso.get()
                        .load(news.urlToImage).into(img);
            }//else
            //img.setBackground(itemView.getResources().getDrawable(R.drawable.shape_rectangle_with_radius));

            link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(news.url));
                    getContext().startActivity(intent);
                }
            });

            itemView.findViewById(R.id.title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CardStackView)itemView.getParent()).performItemClick(MyViewHolder.this);
                }
            });
        }
    }
}
