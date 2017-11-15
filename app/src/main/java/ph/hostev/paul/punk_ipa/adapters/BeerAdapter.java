package ph.hostev.paul.punk_ipa.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ph.hostev.paul.punk_ipa.R;
import ph.hostev.paul.punk_ipa.api.modules.PunkModule;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private List<PunkModule> beerList;

    public BeerAdapter(List<PunkModule> beerList) {
        this.beerList = beerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_item_card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PunkModule beer = beerList.get(position);
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
