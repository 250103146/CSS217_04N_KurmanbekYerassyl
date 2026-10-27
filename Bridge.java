package CSS217;

interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int level);
}
class Radio implements Device {
    @Override public void turnOn() { System.out.println("Radio starts playing static/music."); }
    @Override public void turnOff() { System.out.println("Radio stops playing."); }
    @Override public void setVolume(int level) {
        System.out.println("Radio volume knob turned to: " + level);
    }
}
class SmartLight implements Device {
    @Override public void turnOn() { System.out.println("Smart Light glows bright white."); }
    @Override public void turnOff() { System.out.println("Smart Light shuts off."); }
    @Override public void setVolume(int level) {
        System.out.println("Smart Light brightness set to: " + level + "%");
    }
}
class BasicRemote {
    protected Device device;
    public BasicRemote(Device device) {
        this.device = device;
    }
    public void powerOn()  { device.turnOn(); }
    public void powerOff() { device.turnOff(); }
}
class AdvancedRemote extends BasicRemote {
    public AdvancedRemote(Device device) {
        super(device);
    }
    public void mute() {
        System.out.println("[Remote] Mute button pressed!");
        device.setVolume(0);
    }
}
class PartyRemote extends BasicRemote {
    public PartyRemote(Device device) {
        super(device);
    }
    public void partyMode() {
        System.out.println("[Party Remote] Pumping up the party!");
        device.turnOn();
        device.setVolume(100);
    }
}
interface Weapon {
    void use();
}
class Sword implements Weapon {
    @Override public void use() { System.out.println("Slash! Dealing 20 physical damage."); }
}
class MagicWand implements Weapon {
    @Override public void use() { System.out.println("Zap! Dealing 35 magic damage."); }
}
abstract class Hero {
    protected Weapon weapon;
    public Hero(Weapon weapon) {
        this.weapon = weapon;
    }
}
class Knight extends Hero {
    public Knight(Weapon weapon) { super(weapon); }
    public void attack() { weapon.use(); }
}
class Wizard extends Hero {
    public Wizard(Weapon weapon) { super(weapon); }
    public void attack() { weapon.use(); }
}
public class Bridge {
    public static void main(String[] args) {
        System.out.println("=== Task 4 ===");
        Device light = new SmartLight();
        AdvancedRemote remote = new AdvancedRemote(light);
        remote.powerOn();
        remote.mute();
        System.out.println("\n=== Task 5 ===");
        PartyRemote party = new PartyRemote(new Radio());
        party.partyMode();
        System.out.println("\n=== Task 6 ===");
        new Knight(new Sword()).attack();
        new Knight(new MagicWand()).attack();
    }
}
