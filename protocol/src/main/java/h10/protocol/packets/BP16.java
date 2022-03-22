package h10.protocol.packets;

import h10.protocol.components.StringSimpleUnit;
import h10.protocol.packets.defaults.DefaultAddressableJournalable;
import h10.protocol.rules.*;

import java.util.List;

/**
 * BP16 Real-time locating command（respond：AP16)
 * Запрос на геопозиционирование. Ответ: AP16.
 * После ответа устройство начинает асинхронно присылать
 * AP02 при режиме работы не EMERGENCY,
 * AP01 при режиме работы EMERGENCY
 */
public class BP16 extends DefaultAddressableJournalable { }
