package com.example.personallibrary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class browserAdapter extends RecyclerView.Adapter<browserAdapter.MyViewHolder> {


    private Context mContext;
    private List<Book> mData;
    private RequestOptions options;

    public browserAdapter(Context mContext, List<Book> mData) {
        this.mContext = mContext;
        this.mData = mData;

        //Request option for Glide
        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.book_row_browse , parent , false);
        final MyViewHolder viewHolder =  new MyViewHolder(view);
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext , BookActivity.class);
                int pos = viewHolder.getAdapterPosition();
                i.putExtra("book_title" ,mData.get(pos).getTitle());
                i.putExtra("book_author" ,mData.get(pos).getAuthors());
                i.putExtra("book_desc" ,mData.get(pos).getDescription());
                i.putExtra("book_categories" ,mData.get(pos).getCategories());
                i.putExtra("book_publish_date" ,mData.get(pos).getPublishedDate());
                i.putExtra("book_info" ,mData.get(pos).getmUrl());
                i.putExtra("book_buy" ,mData.get(pos).getBuy());
                i.putExtra("book_preview" ,mData.get(pos).getPerview());
                i.putExtra("book_thumbnail" ,mData.get(pos).getThumbnail());


                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        Book book = mData.get(i);
        holder.tvTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthors());
        holder.tvPrice.setText(book.getPrice());
        holder.tvCategory.setText(book.getCategories());

        //load image from internet and set it into imageView using Glide
        Glide.with(mContext).load(book.getThumbnail()).apply(options).into(holder.ivThumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView ivThumbnail ;
        TextView tvTitle , tvCategory , tvPrice , tvAuthor;
        LinearLayout container ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivThumbnail = itemView.findViewById(R.id.thumbnail);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor = itemView.findViewById(R.id.author);
            tvCategory = itemView.findViewById(R.id.category);
            tvPrice = itemView.findViewById(R.id.price);
            container = itemView.findViewById(R.id.container);


        }
    }

    /* ViewHolder inner class
    public class BookHolder extends RecyclerView.ViewHolder
    {
        // fields
        TextView title_TV;
        TextView price_TV;
        TextView currentNumber_TV;
        TextView author_TV;
        TextView pubDate_TV;
        TextView category_TV;
        ImageView bookCover_IV;

        // constructor
        public BookHolder(@NonNull View itemView)
        {
            super(itemView);
            title_TV = itemView.findViewById(R.id.textView_bookTitle);
            price_TV = itemView.findViewById(R.id.textView_bookPrice);
            currentNumber_TV = itemView.findViewById(R.id.textView_currentNumber);
            author_TV = itemView.findViewById(R.id.textView_bookAuthor);
            pubDate_TV = itemView.findViewById(R.id.textView_bookPubDate);
            category_TV = itemView.findViewById(R.id.textView_bookCategory);
            bookCover_IV = itemView.findViewById(R.id.imageView_BookCover);
        }
    }

    // member variables
    private List<Book> bookList;

    // constructor for BookAdapter
    public browserAdapter(List<Book> bookList)
    {
        this.bookList = bookList;
    }

    // mandatory methods to implement
    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View bookView = inflater.inflate(R.layout.book_row_browse, parent, false);

        return new BookHolder(bookView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position)
    {
        Book aBook = bookList.get(position);

        holder.title_TV.setText(aBook.getTitle());
        TextView price = holder.price_TV;

        if (aBook.getRetailPrice() == -1.0)
        {
            price.setText("NA");
        }
        else
        {
            price.setText(String.valueOf(aBook.getRetailPrice() + "\nRON"));
        }

        holder.currentNumber_TV.setText(String.valueOf(position + 1));
        holder.author_TV.setText(String.valueOf("by " + aBook.getAuthor()));
        holder.pubDate_TV.setText(aBook.getPubDate());
        holder.category_TV.setText(aBook.getCategory());

        ImageView bookCover = holder.bookCover_IV;

        String tempImageURL;

        if (!aBook.getImageURL().equals("NA"))
        {
            tempImageURL = aBook.getImageURL().replace("http:", "https:");

            Picasso
                    .get()
                    .load(tempImageURL)
                    .into(bookCover);
        }
    }

    @Override
    public int getItemCount()
    {
        return bookList.size();
    }*/
}
