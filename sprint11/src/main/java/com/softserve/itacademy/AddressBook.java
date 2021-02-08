package com.softserve.itacademy;

import java.util.*;

public class AddressBook implements Iterable<String> {
    private static AddressBook instance = null;
    public static AddressBook getInstance() {
        return instance != null ? instance : (instance = new AddressBook(10));
    }

    public enum SortOrder {
        ASC, DESC
    }

    private NameAddressPair[] records;
    private int size = 0;

    private static class NameAddressPair {
        private final Person person;
        private String address;

        private NameAddressPair(Person person, String address) {
            Objects.requireNonNull(person);
            Objects.requireNonNull(address);
            this.person = person;
            this.address = address;
        }

        private static class Person {
            private final String firstName;
            private final String lastName;

            private Person(String firstName, String lastName) {
                Objects.requireNonNull(firstName);
                Objects.requireNonNull(lastName);
                this.firstName = firstName;
                this.lastName = lastName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Person person = (Person) o;
                return Objects.equals(firstName, person.firstName) &&
                        Objects.equals(lastName, person.lastName);
            }

            @Override
            public int hashCode() {
                return Objects.hash(firstName, lastName);
            }
        }
    }

    private static final String ITERATOR_FORMAT = "First name: %s, Last name: %s, Address: %s";

    private class AddressBookIterator implements Iterator<String> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public String next() {
            if (hasNext()) {
                NameAddressPair res = records[cursor++];
                return String.format(ITERATOR_FORMAT,
                        res.person.firstName, res.person.lastName, res.address);
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    private AddressBook(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity should be positive");
        }
        records = new NameAddressPair[capacity];
    }

    private int find(NameAddressPair.Person person) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(person, records[i].person)) {
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity() {
        if (size + 1 > records.length) {
            records = Arrays.copyOf(records, 2 * records.length);
        }
    }

    public boolean create(String firstName, String lastName, String address) {
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        if (find(person) > -1) {
            return false;
        }
        ensureCapacity();
        records[size++] = new NameAddressPair(person, address);
        return true;
    }

    public String read(String firstName, String lastName) {
        int ix = find(new NameAddressPair.Person(firstName, lastName));
        return ix > -1 ? records[ix].address : null;
    }

    public boolean update(String firstName, String lastName, String address) {
        int ix = find(new NameAddressPair.Person(firstName, lastName));
        if (ix > -1) {
            records[ix].address = address;
            return true;
        }
        return false;
    }

    public boolean delete(String firstName, String lastName) {
        int ix = find(new NameAddressPair.Person(firstName, lastName));
        if (ix == -1) {
            return false;
        } else if (ix < size - 1) {
            System.arraycopy(records, ix + 1, records, ix, size - ix - 1);
        }
        records[--size] = null;
        return true;
    }

    public int size() {
        return size;
    }

    public void sortedBy(SortOrder order) {
        final Comparator<String> comp = order == SortOrder.ASC ? Comparator.naturalOrder()
                        : order == SortOrder.DESC ? Comparator.reverseOrder()
                        : null;
        Arrays.sort(records, 0, size,
                (nap1, nap2) -> {
                    int res = Objects.compare(nap1.person.firstName, nap2.person.firstName, comp);
                    if (res == 0) {
                        res = Objects.compare(nap1.person.lastName, nap2.person.lastName, comp);
                    }
                    return res;
                }
        );
    }

    @Override
    public Iterator<String> iterator() {
        return this.new AddressBookIterator();
    }
}