package ph.hostev.paul.punk_ipa.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import ph.hostev.paul.punk_ipa.beans.Beer;

import static ph.hostev.paul.punk_ipa.Constants.DATABASE_NAME;
import static ph.hostev.paul.punk_ipa.Constants.DATABASE_VERSION;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private Dao<Beer, Integer> beer = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Beer.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Beer.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Beer, Integer> getBeerDao() throws SQLException {
        if (beer == null) beer = getDao(Beer.class);
        return beer;
    }

    @Override
    public void close() {
        beer = null;
        super.close();
    }
}
