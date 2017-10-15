package ru.proghouse.cayenne;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;
import ru.proghouse.cayenne.persistent.Artist;
import ru.proghouse.cayenne.persistent.Gallery;
import ru.proghouse.cayenne.persistent.Painting;

import java.util.*;

public class Main {

    public static void main(String[] args){

        // Создаем подключение и открываем сессию
        ServerRuntime cayenneRuntime = new ServerRuntime("cayenne-project.xml");
        ObjectContext context = cayenneRuntime.getContext();

        // Создаем объекты
        Artist picasso = context.newObject(Artist.class);
        picasso.setName("Пабло Пикассо");
        picasso.setDateOfBirth(new Date());

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
        System.out.println("--------- Запрос на выгрузку без параметров ------------");
        SelectQuery select1 = new SelectQuery(Painting.class);
        List<Painting> paintings1 = context.performQuery(select1);
        for(Painting item: paintings1){
            System.out.println(item.getName());
        }

        System.out.println("--------- Запрос на выгрузку с параметром ------------");
        Expression qualifier2 = ExpressionFactory.likeIgnoreCaseExp(Painting.NAME_PROPERTY,"де%");
        SelectQuery select2 = new SelectQuery(Painting.class, qualifier2);
        List<Painting> paintings2 = context.performQuery(select2);
        for(Painting item: paintings2){
            System.out.println(item.getName());
        }

        System.out.println("--------- Запрос на выгрузку картин художник которых старше 100 лет ------------");
        Calendar c = new GregorianCalendar();
        c.set(c.get(Calendar.YEAR) - 100, 0, 1, 0, 0, 0);
        Expression qualifier3 = Expression.fromString("artistPainting.dateOfBirth < $date");
        qualifier3 = qualifier3.expWithParameters(Collections.singletonMap("date", c.getTime()));
        SelectQuery select3 = new SelectQuery(Painting.class, qualifier3);
        List<Painting> paintings3 = context.performQuery(select3);
        for(Painting item: paintings3){
            System.out.println(item.getName());
        }

        System.out.println("--------- Запрос на выгрузку картин с несколькими условиями ------------");
        Expression qualifier4_1 = ExpressionFactory.likeIgnoreCaseExp(Painting.NAME_PROPERTY,"де%");
        Expression qualifier4_2 = ExpressionFactory.likeIgnoreCaseExp(Painting.NAME_PROPERTY,"ск%");
        Expression qualifier4 = ExpressionFactory.joinExp(Expression.OR, Arrays.asList(qualifier4_1, qualifier4_2));
        SelectQuery select4 = new SelectQuery(Painting.class, qualifier4);
        List<Painting> paintings4 = context.performQuery(select4);
        for(Painting item: paintings4){
            System.out.println(item.getName());
        }

        System.out.println("--------- Запрос на удаление художника ------------");
        Expression qualifier = ExpressionFactory.matchExp(Artist.NAME_PROPERTY, "Пабло Пикассо");
        SelectQuery select = new SelectQuery(Artist.class, qualifier);
        Artist deletingPicasso = (Artist) Cayenne.objectForQuery(context, select);
        if (deletingPicasso != null)
        {
            context.deleteObject(deletingPicasso);
            context.commitChanges();
        }
    }
}
