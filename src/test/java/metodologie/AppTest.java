package metodologie;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AppTest {
    static int MEMORY = 1474560;

    @Test
    public void testValidPlace1() {
        Floppy floppyDisk = App.floppyDisk();
        try {
            floppyDisk.placeHead(MEMORY - 100);
        } catch (OutOfMemory e) {
            fail("Didn't expect an exception");
        }
    }

    @Test
    public void testValidPlace2() {
        Floppy floppyDisk = App.floppyDisk();
        try {
            floppyDisk.placeHead(202095);
        } catch (OutOfMemory e) {
            fail("Didn't expect an exception");
        }
    }

    @Test
    public void testOutOfMemoryPlace1() {
        Floppy floppyDisk = App.floppyDisk();
        try {
            floppyDisk.placeHead(MEMORY + 10);
            fail("Expected an OutOfMemory exception!");
        } catch (OutOfMemory e) {
        }
    }

    @Test
    public void testOutOfMemoryPlace2() {
        Floppy floppyDisk = App.floppyDisk();
        try {
            floppyDisk.placeHead(-1000);
            fail("Expected an OutOfMemory exception!");
        } catch (OutOfMemory e) {
        }
    }

    @Test
    public void testValidRead1() {
        Floppy floppyDisk = App.floppyDisk();
        try {
            floppyDisk.placeHead(1000);

            List<Byte> bytes = new ArrayList<>();
            for (int index = 0; index < 10000; index++)
                bytes.add((byte) (index % 255));

            floppyDisk.write(bytes);
            List<Byte> read = floppyDisk.read(9910);
            for (int i = 0; i < 9910; i++)
                assertEquals(bytes.get(i), read.get(i));
        } catch (WriteBlockActive e) {
            fail("Didn't expect a WriteBlock exception");
        } catch (UndefinedMemory e) {
            fail("Didn't expect an UndefinedMemory exception");
        } catch (OutOfMemory e) {
            fail("Didn't expect an OutOfMemory exception");
        }
    }

    @Test
    public void testOutOfMemoryRead1() {
        Floppy floppyDisk = App.floppyDisk();
        try {
            floppyDisk.placeHead(MEMORY - 100);

            List<Byte> bytes = new ArrayList<>();
            for (int index = 0; index < 99; index++)
                bytes.add((byte) (index % 255));

            floppyDisk.write(bytes);
            floppyDisk.read(9910);
            fail("Expected an OutOfMemory exception");
        } catch (WriteBlockActive e) {
            fail("Didn't expect a WriteBlock exception");
        } catch (UndefinedMemory e) {
            fail("Didn't expect an UndefinedMemory exception");
        } catch (OutOfMemory e) {
        }
    }

    @Test
    public void testUndefinedMemoryRead1() {
        Floppy floppyDisk = App.floppyDisk();
        try {
            floppyDisk.placeHead(2424);

            List<Byte> bytes = new ArrayList<>();
            for (int index = 0; index < 100; index++)
                bytes.add((byte) (index % 255));

            floppyDisk.write(bytes);
            floppyDisk.read(8423);
            fail("Expected an UndefinedMemory exception");
        } catch (WriteBlockActive e) {
            fail("Didn't expect a WriteBlock exception");
        } catch (UndefinedMemory e) {
        } catch (OutOfMemory e) {
            fail("Didn't expect an OutOfMemory exception");
        }
    }

    @Test
    public void testValidWrite1() {
        Floppy floppyDisk = App.floppyDisk();
        try {
            floppyDisk.placeHead(291800);
            floppyDisk.activateWriteBlock();

            List<Byte> bytes = new ArrayList<>();
            for (int index = 0; index < 901; index++)
                bytes.add((byte) (index % 255));

            floppyDisk.deactivateWriteBlock();
            floppyDisk.write(bytes);
        } catch (WriteBlockActive e) {
            fail("Didn't expect a WriteBlock exception");
        } catch (OutOfMemory e) {
            fail("Didn't expect an OutOfMemory exception");
        }
    }

    @Test
    public void testOutOfMemoryWrite1() {
        Floppy floppyDisk = App.floppyDisk();
        try {
            floppyDisk.placeHead(MEMORY - 100);

            List<Byte> bytes = new ArrayList<>();
            for (int index = 0; index < 512; index++)
                bytes.add((byte) (index % 255));

            floppyDisk.write(bytes);
            fail("Expected an OutOfMemory exception");
        } catch (WriteBlockActive e) {
            fail("Didn't expect a WriteBlock exception");
        } catch (OutOfMemory e) {
        }
    }

    @Test
    public void testWriteBlockActiveWrite1() {
        Floppy floppyDisk = App.floppyDisk();
        try {
            floppyDisk.activateWriteBlock();
            floppyDisk.placeHead(291800);

            List<Byte> bytes = new ArrayList<>();
            for (int index = 0; index < 901; index++)
                bytes.add((byte) (index % 255));

            floppyDisk.write(bytes);
            fail("Expected a WriteBlock exception");
        } catch (WriteBlockActive e) {
        } catch (OutOfMemory e) {
            fail("Didn't expect an OutOfMemory exception");
        }
    }

    @Test
    public void testValidFormat1() {
        Floppy floppyDisk = App.floppyDisk();

        try {
            floppyDisk.format();
        } catch (WriteBlockActive e) {
            fail("Didn't expect a WriteBlockActive exception");
        }
    }

    @Test
    public void testWriteBlockFormat1() {
        Floppy floppyDisk = App.floppyDisk();

        try {
            floppyDisk.activateWriteBlock();
            floppyDisk.format();
            fail("Expected a WriteBlockActive exception");
        } catch (WriteBlockActive e) {
        }
    }
}
