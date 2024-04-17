package utilities;

import initials.Car;
import initials.Coordinates;
import initials.HumanBeing;
import initials.WeaponType;

/**
 * Класс для работы с XML
 * @author Ubica228
 */
public class XML {
    public static String humanToXML(HumanBeing human){
        return "<human_"+human.getId()+" name='"+human.getName()+"'"+" id='"+human.getId()+"' "+" realHero='"+human.getRealHero()+"' "+" hasToothpick='"+human.getHasToothpick()+"' "+" impactSpeed='"+human.getImpactSpeed()+"' "+" soundtrackName='"+human.getSoundtrackName()+"' "+" weaponType='"+human.getWeaponType()+"' "+" minutesOfWaiting='"+human.getMinutesOfWaiting()+"' X='"+human.getCoordinates().getX() +"' Y='"+human.getCoordinates().getY()+"' carName='"+human.getCar().getName()+"' creationTime='"+human.getCreationDate()+"'"+"/>";
    }
    public static HumanBeingDAO XMLToHuman(String[] xmlForm){
        HumanBeingDAO humanBeings = new HumanBeingDAO();
        for(String xmlString:xmlForm){
            int id = Integer.parseInt(xmlString.split("id='")[1].split("'")[0]);
            String name = xmlString.split("name='")[1].split("'")[0];
            double x = Double.parseDouble(xmlString.split("X='")[1].split("'")[0]);
            long y = Long.parseLong(xmlString.split("Y='")[1].split("'")[0]);
            java.time.ZonedDateTime creationDate = java.time.ZonedDateTime.parse(xmlString.split("creationTime='")[1].split("'")[0]); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
            boolean realHero = Boolean.parseBoolean(xmlString.split("realHero='")[1].split("'")[0]);
            boolean hasToothpick = Boolean.parseBoolean(xmlString.split("hasToothpick='")[1].split("'")[0]);
            float impactSpeed = Float.parseFloat(xmlString.split("impactSpeed='")[1].split("'")[0]);
            String soundtrackName = xmlString.split("soundtrackName='")[1].split("'")[0];
            long minutesOfWaiting = Long.parseLong(xmlString.split("minutesOfWaiting='")[1].split("'")[0]); ;
            WeaponType weaponType = WeaponType.valueOf(xmlString.split("weaponType='")[1].split("'")[0]);
            String carName = xmlString.split("carName='")[1].split("'")[0];

            humanBeings.add(new HumanBeing(id,name,new Coordinates(x,y),realHero,hasToothpick,impactSpeed,soundtrackName,minutesOfWaiting,weaponType,new Car(carName),creationDate));

        }
        return humanBeings;

    }
    public static String toXML(HumanBeingDAO humansList){
        String res = "<root>\n";
        for(HumanBeing x:humansList.getHumanBeings()) res += humanToXML(x) +"\n";
        res+="</root>";
        return res;
    }
}
