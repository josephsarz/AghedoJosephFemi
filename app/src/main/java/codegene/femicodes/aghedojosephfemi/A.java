package codegene.femicodes.aghedojosephfemi;

import androidx.lifecycle.LiveData;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

import codegene.femicodes.aghedojosephfemi.local.entity.CarOwnerEntity;

class A {

    public static void main(String[] args) {


    }

    interface RawDao {
        @RawQuery(observedEntities = CarOwnerEntity.class)
        LiveData<List<CarOwnerEntity>> getUsers(SupportSQLiteQuery query);
    }
}
