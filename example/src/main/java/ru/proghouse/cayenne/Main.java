package ru.proghouse.cayenne;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.SelectQuery;
import ru.proghouse.cayenne.persistent.Artist;
import ru.proghouse.cayenne.persistent.Gallery;
import ru.proghouse.cayenne.persistent.Painting;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args){

        // Создаем подключение и открываем сессию
        ServerRuntime cayenneRuntime = new ServerRuntime("cayenne-project.xml");
        ObjectContext context = cayenneRuntime.getContext();

        // Создаем объекты
        Artist picasso = context.newObject(Artist.class);
        picasso.setName("Пабло Пикассо");
        picasso.setDateOfBirthString("18811025");

        Gallery pushkin = context.newObject(Gallery.class);
        pushkin.setName("Музей изобразительных искусств им. А.С. Пушкина");
        pushkin.setDateOfBirth(new Date());

        Painting girl = context.newObject(Painting.class);
        girl.setName("Девочка на шаре");

        Painting violin = context.newObject(Painting.class);
        violin.setName("Скрипка");

        // Связываем объекты
        picasso.addToPaintingArtist(girl);
        picasso.addToPaintingArtist(violin);

        girl.setGalleryPainting(pushkin);
        girl.setGalleryPainting(pushkin);

        // Коммитим изменения в БД
        context.commitChanges();

        // Делаем запрос на выгрузку всех объектов типа Painting
        SelectQuery select1 = new SelectQuery(Painting.class);
        List<Painting> paintings1 = context.performQuery(select1);
        for(Painting item: paintings1){
            System.out.println(item.getName());
        }
    }
}
