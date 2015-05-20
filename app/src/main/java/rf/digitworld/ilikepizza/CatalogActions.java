package rf.digitworld.ilikepizza;

import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Дмитрий on 06.03.2015.
 */
public class CatalogActions {
    ArrayList<Action> actionList=new ArrayList<>();
    SharedPreferences sp;


    public CatalogActions() {
        String[] names={"Нам 1 месяц ","Розыгрыш пиццы","Розыгрыш пиццы"};

        String[] descriptions={"Всем доброе утро! У нас сегодня маленький праздник :) I like pizza исполняется 1 месяц. \n" +
                "За это короткое, но приятное и продуктивное время мы познакомились с вами, подарили 12 пицц, и накормили большое количество жителей Ачинска! \n" +
                "Спасибо, за то, что вы с нами вместе! \n" +
                "Прекрасного настроения и лёгкого понедельника!",
                "Доброе утро, друзья! Впереди выходные и \"I like pizza\" запускает розыгрыш пиццы на тонком тесте! Уже в воскресенье 22 февраля в 14.00 мы определим двух счастливчиков, которые получат по одной пицце! \n" +
                        "\n" +
                        "Чтобы испытать свое везение, необходимо:\n" +
                        "1. Поделиться этой новостью с друзьями;\n" +
                        "2. Состоять в нашей группе;\n" +
                        "3. Состоять в группе - «Кафе Кунжут»;\n" +
                        "4. Быть в друзьях у - Алексея Тароватова.\n" +
                        "\n" +
                        "Итоги подведем с помощью приложения random.app\n" +
                        "\n" +
                        "Всем удачи и прекрасных выходных!",
                "Уважаемые друзья! Для того, чтобы встретить весну более приятно\n" +
                        "\"I like pizza\" запускает розыгрыш двух пицц на тонком тесте!\n" +
                        "Уже в воскресенье 1 марта в 14.00 мы определим двух победителей!\n" +
                        "\n" +
                        "Принять участие просто:\n" +
                        "1. Поделиться этой новостью с друзьями;\n" +
                        "2. Состоять в нашей группе;\n" +
                        "3. Состоять в группе -«Кафе Кунжут»;\n" +
                        "4. Быть в друзьях у - Алексея Тароватова.\n" +
                        "\n" +
                        "Итоги подведем с помощью приложения random.app\n" +
                        "\n" +
                        "Всем удачи и весеннего настроения!"};
        for(int i=0;i<names.length; i++) {

            Action action=new Action(names[i],descriptions[i]);
            action.setId(i);
            addAction(action);
        }

    }
    public void addAction(Action action){

        actionList.add(action);
    }

    public ArrayList<Action> getActions(){

        return actionList;

    }
    public int getcount(){

        return  actionList.size();
    }

    public void saveActionList(){

    }
    public Action getAction(int id){
        return actionList.get(id);
    }
    public void delAction(int id){
        actionList.remove(id);
    }
    public ArrayList<Action> getActionList(){
        return actionList;
    }

}

