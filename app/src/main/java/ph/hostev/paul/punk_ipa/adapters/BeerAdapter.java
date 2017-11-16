package ph.hostev.paul.punk_ipa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ph.hostev.paul.punk_ipa.R;
import ph.hostev.paul.punk_ipa.beans.Beer;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private List<Beer> beerList;
    private Context context;

    public BeerAdapter(List<Beer> beerList, Context context) {
        this.context = context;
        this.beerList = beerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_item_card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Beer beer = beerList.get(position);

        Picasso.with(context)
                .load(beer.getImageUrl())
//                .memoryCache(new LruCache(24000))
                .resize(50, 100)
                .centerCrop()
                .into(holder.beerCover);

        holder.name.setText(beer.getName());
        holder.tagline.setText(beer.getTagline());
        holder.brewed.setText(beer.getFirstBrewed());
        holder.abv.setText(String.valueOf(beer.getAbv()));
        holder.ibu.setText(String.valueOf(beer.getIbu()));
        holder.ebc.setText(String.valueOf(beer.getEbc()));
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView beerCover;
        TextView name;
        TextView tagline;
        TextView brewed;
        TextView abv;
        TextView ibu;
        TextView ebc;

        ViewHolder(View itemView) {
            super(itemView);
            beerCover = itemView.findViewById(R.id.beer_cover);
            name = itemView.findViewById(R.id.name);
            tagline = itemView.findViewById(R.id.tagline);
            brewed = itemView.findViewById(R.id.first_brewed);
            abv = itemView.findViewById(R.id.abv);
            ibu = itemView.findViewById(R.id.ibu);
            ebc = itemView.findViewById(R.id.ebc);
        }
    }
}
