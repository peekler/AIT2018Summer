package hu.ait.android.aitforumapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.ait.android.aitforumapp.R;
import hu.ait.android.aitforumapp.data.Post;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> postList;
    private List<String> postKeys;
    private String uId;
    private int lastPosition = -1;

    public PostsAdapter(Context context, String uId) {
        this.context = context;
        this.uId = uId;

        postList = new ArrayList<Post>();
        postKeys = new ArrayList<String>();

        /*for (int i = 0; i < 5; i++) {
            postList.add(new Post(
                    "123", "John Snow",
                    "Warning",
                    "Winter is coming"
            ));
        }*/
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(
                parent.getContext()).inflate(
                        R.layout.row_post, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = postList.get(holder.getAdapterPosition());
        holder.tvAuthor.setText(post.getAuthor());
        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());

        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context,
                    android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void addPost(Post post, String key) {
        postList.add(post);
        postKeys.add(key);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvAuthor;
        public TextView tvTitle;
        public TextView tvBody;

        public ViewHolder(View itemView) {
            super(itemView);

            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }
}
