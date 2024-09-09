
package Client.event;


public class PublicEvent {
    private static PublicEvent instance;
    private EventChat eventChat;
    private EventMenuLeft eventMenuLeft;
    
    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {

    }

     public void addEventChat(EventChat event) {
        this.eventChat = event;
    }

    public void addEventMenuLeft(EventMenuLeft event) {
        this.eventMenuLeft = event;
    }
     
    public EventChat getEventChat() {
        return eventChat;
    }
    
    public EventMenuLeft getEventMenuLeft() {
        return eventMenuLeft;
    }

   
}
