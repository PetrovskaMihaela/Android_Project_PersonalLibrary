package com.example.personallibrary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ylAdapter extends RecyclerView.Adapter<ylAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<BookA> books;

    ylAdapter(Context context, List<BookA> books){
        this.inflater = LayoutInflater.from(context);
        this.books = books;

    }

    @NonNull
    @Override
    public ylAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_book_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ylAdapter.ViewHolder viewHolder, int i) {

        String title = books.get(i).getTitle();
        String author = books.get(i).getAuthor();
        String date = books.get(i).getDate();
        String finishDate = books.get(i).getFinishDate();
        long id = books.get(i).getBookId();

        viewHolder.bTitle.setText(title);
        viewHolder.baDate.setText(date);
        viewHolder.bAuthor.setText(author);
        viewHolder.bfDate.setText(finishDate);
        viewHolder.bId.setText(String.valueOf(books.get(i).getBookId()));

        viewHolder.bTitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView tv = (TextView) v;
                Toast.makeText(v.getContext(), tv.getText(), Toast.LENGTH_SHORT).show();
                /*Intent i = new Intent(viewHolder.itemView.getContext(), DetailsBook.class);
                //   i.putExtra("id", books.get(getAdapterPosition()).getBookId());
                i.putExtra("id", books.get(parseInt(viewHolder.bId.getText().toString())-1).getBookId());
                viewHolder.itemView.getContext().startActivity(i);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView bTitle, bAuthor, baDate, bfDate, bId;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            bTitle = itemView.findViewById(R.id.card_title);
            bAuthor = itemView.findViewById(R.id.card_author);
            baDate = itemView.findViewById(R.id.card_ad);
            bfDate = itemView.findViewById(R.id.card_fd);
            bId = itemView.findViewById(R.id.card_id);

           itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), DetailsBook.class);
                    i.putExtra("id", books.get(getAdapterPosition()).getBookId());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
