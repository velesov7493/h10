package h10.protocol.packets;

import h10.protocol.subunits.AlarmEntry;
import h10.protocol.subunits.ReminderType;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.*;

public class Pdu85Test {

    @Test
    public void whenApParse() {
        String in = "IWAP85,080835,1,3,0900,135,1,1@0900,135,1,2@0900,135,1,3#";
        ProtocolDataUnit pdu = new ProtocolDataUnit("AP85");
        pdu.parse(in);
        AP85 content = pdu.getContent();
        assertEquals(80835, content.getJournalNo());
        assertTrue(content.isAllEnadled());
        assertEquals(LocalTime.of(9, 0), content.getAlarm(0).getTime());
        assertArrayEquals(new int[] {1, 3 , 5}, content.getAlarm(0).getDaysOfWeek());
        assertTrue(content.getAlarm(0).isEnabled());
        assertEquals(ReminderType.TAKE_THE_MEDICINE, content.getAlarm(0).getType());
        assertEquals(LocalTime.of(9, 0), content.getAlarm(1).getTime());
        assertArrayEquals(new int[] {1, 3 , 5}, content.getAlarm(1).getDaysOfWeek());
        assertTrue(content.getAlarm(1).isEnabled());
        assertEquals(ReminderType.DRINK_WATER, content.getAlarm(1).getType());
        assertEquals(LocalTime.of(9, 0), content.getAlarm(2).getTime());
        assertArrayEquals(new int[] {1, 3 , 5}, content.getAlarm(2).getDaysOfWeek());
        assertTrue(content.getAlarm(2).isEnabled());
        assertEquals(ReminderType.SEDENTARY, content.getAlarm(2).getType());
    }

    @Test
    public void whenBpPack() {
        String expected = "IWBP85,353456789012345,080835,1,3,0900,135,1,1@0900,135,1,2@0900,135,1,3#";
        BP85 content = new BP85();
        ProtocolDataUnit pdu = new ProtocolDataUnit("BP85", content);
        AlarmEntry[] e = new AlarmEntry[3];
        e[0] = new AlarmEntry();
        e[0].setEnabled(true);
        e[0].setTime(LocalTime.of(9, 0));
        e[0].setDaysOfWeek(new int[] {1, 3, 5});
        e[0].setType(ReminderType.TAKE_THE_MEDICINE);
        e[1] = new AlarmEntry();
        e[1].setEnabled(true);
        e[1].setTime(LocalTime.of(9, 0));
        e[1].setDaysOfWeek(new int[] {1, 3, 5});
        e[1].setType(ReminderType.DRINK_WATER);
        e[2] = new AlarmEntry();
        e[2].setEnabled(true);
        e[2].setTime(LocalTime.of(9, 0));
        e[2].setDaysOfWeek(new int[] {1, 3, 5});
        e[2].setType(ReminderType.SEDENTARY);
        content.setImei("353456789012345");
        content.setJournalNo(80835);
        content.setAllEnabled(true);
        content
                .addAlarm(e[0])
                .addAlarm(e[1])
                .addAlarm(e[2]);
        assertEquals(expected, pdu.toPacket());
    }
}
