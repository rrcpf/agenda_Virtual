import java.util.ArrayList;
import java.util.Iterator;

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
        agendas.add(agenda)
    }

    public void remove_user(int id){
        this.agendas.removeIf(u -> u.getID() == id);
    }
}
