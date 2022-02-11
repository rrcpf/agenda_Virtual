import java.util.*;
import java.util.stream.*;

public class ScheduleData {
    private ArrayList<ISchedule> agendas;

    public ScheduleData(){
        agendas = new ArrayList<ISchedule>();
    }

    public ISchedule getSchedule(int id){
        Iterator<ISchedule> it = this.agendas.iterator();
        while(it.hasNext()) {
            ISchedule i = it.next();
            if(i.getID() == id) {
                return i;
            }
        }
        return null;
    }

    public ISchedule getSchedule(String name, IUser creator){
        Iterator<ISchedule> it = this.agendas.iterator();
        while(it.hasNext()) {
            ISchedule i = it.next();
            if(i.getName().equals(name) && i.getCreator().getId() == creator.getId()) {
                return i;
            }
        }

        return null;
    }

    public void addSchedule(String name, IUser creator){
        this.agendas.add(new Schedule(this.agendas.size(), name, creator));
    }

    public boolean removeSchedule(String name, IUser criador){
        return this.agendas.removeIf(a -> a.getName().equals(name)
                && a.getCreator().getId() == criador.getId());
    }

    public ArrayList<ISchedule> listSchedules(int id) {
        return this.agendas.stream().filter(s -> s.getCreator().getId() == id
                || Arrays.asList(s.getSharedUsers().stream().map(u -> u.getId()).toArray()).contains((Object)id))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
