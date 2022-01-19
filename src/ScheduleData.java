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

    public IAgenda getAgenda(String name, IUsuario creator){
        Iterator<IAgenda> it = this.agendas.iterator();
        while(it.hasNext()) {
            IAgenda i = it.next();
            if(i.getName().equals(name) && i.getCreator().getId() == creator.getId()) {
                return i;
            }
        }

        return null;
    }

    public void addSchedule(String name, IUsuario creator){
        this.agendas.add(new Agenda(this.agendas.size(), name, creator));
    }

    public boolean removeSchedule(String name, IUsuario criador){
        return this.agendas.removeIf(a -> a.getName().equals(name)
                && a.getCreator().getId() == criador.getId());
    }

    public ArrayList<IAgenda> listSchedules(int id) {
        return this.agendas.stream().filter(s -> s.getCreator().getId() == id
                || Arrays.asList(s.getSharedUsers().stream().map(u -> u.getId()).toArray()).contains((Object)id))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
