import java.util.*;
import java.util.stream.*;

public class ScheduleData {
    private ArrayList<IAgenda> agendas;

    public ScheduleData(){
        agendas = new ArrayList<IAgenda>();
    }

    public IAgenda getAgenda(int id){
        Iterator<IAgenda> it = this.agendas.iterator();
        while(it.hasNext()) {
            IAgenda i = it.next();
            if(i.getID() == id) {
                return i;
            }
        }
        return null;
    }

    public IAgenda getAgenda(String name){
        Iterator<IAgenda> it = this.agendas.iterator();
        while(it.hasNext()) {
            IAgenda i = it.next();
            if(i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    public void addSchedule(IAgenda agenda){
        agendas.add(agenda);
    }

    public void removeSchedule(int id){
        this.agendas.removeIf(u -> u.getID() == id);
    }

    public ArrayList<IAgenda> listSchedules(int id){
        return this.agendas.stream().filter(s -> s.getCreator().getId() == id || Arrays.asList(s.getSharedUsers().stream().map(u -> u.getId()).toArray()).contains((Object)id)).collect(Collectors.toCollection(ArrayList::new));
    }
}
