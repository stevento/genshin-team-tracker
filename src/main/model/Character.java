package model;

import exceptions.IllegalCharacterException;

// Represents a character from Genshin Impact including name, element, weapon type, and rarity
public enum Character {
    ALBEDO("Albedo", Element.GEO, WeaponType.SWORD, 5),
    AMBER("Amber", Element.PYRO, WeaponType.BOW, 4),
    BARBARA("Barbara", Element.HYDRO, WeaponType.CATALYST, 4),
    BEIDOU("Beidou", Element.ELECTRO, WeaponType.CLAYMORE, 4),
    BENNETT("Bennett", Element.PYRO, WeaponType.SWORD, 4),
    CHONGYUN("Chongyun", Element.CRYO, WeaponType.CLAYMORE, 4),
    DILUC("Diluc", Element.PYRO, WeaponType.CLAYMORE, 5),
    DIONA("Diona", Element.CRYO, WeaponType.BOW, 4),
    EULA("Eula", Element.CRYO, WeaponType.CLAYMORE, 5),
    FISCHL("Fischl", Element.ELECTRO, WeaponType.BOW, 4),
    GANYU("Ganyu", Element.CRYO, WeaponType.BOW, 5),
    HUTAO("HuTao", Element.PYRO, WeaponType.POLEARM, 5),
    JEAN("Jean", Element.ANEMO, WeaponType.SWORD, 5),
    KAZUHA("Kazuha", Element.ANEMO, WeaponType.SWORD, 5),
    KAEYA("Kaeya", Element.CRYO, WeaponType.SWORD, 4),
    AYAKA("Ayaka", Element.CRYO, WeaponType.SWORD, 5),
    KEQING("Keqing", Element.ELECTRO, WeaponType.SWORD, 5),
    KLEE("Klee", Element.PYRO, WeaponType.CATALYST, 5),
    LISA("Lisa", Element.ELECTRO, WeaponType.CATALYST, 4),
    MONA("Mona", Element.HYDRO, WeaponType.CATALYST, 5),
    NINGGUANG("Ningguang", Element.GEO, WeaponType.CATALYST, 4),
    NOELLE("Noelle", Element.GEO, WeaponType.CLAYMORE, 4),
    QIQI("Qiqi", Element.CRYO, WeaponType.SWORD, 5),
    RAZOR("Razor", Element.ELECTRO, WeaponType.CLAYMORE, 4),
    ROSARIA("Rosaria", Element.CRYO, WeaponType.POLEARM, 4),
    SUCROSE("Sucrose", Element.ANEMO, WeaponType.CATALYST, 4),
    TARTAGLIA("Tartaglia", Element.HYDRO, WeaponType.BOW, 5),
    ANEMOTRAVELER("AnemoTraveler", Element.ANEMO, WeaponType.SWORD, 5),
    GEOTRAVELER("GeoTraveler", Element.GEO, WeaponType.SWORD, 5),
    ELECTROTRAVELER("ElectroTraveler", Element.ELECTRO, WeaponType.SWORD, 5),
    VENTI("Venti", Element.ANEMO, WeaponType.BOW, 5),
    XIANGLING("Xiangling", Element.PYRO, WeaponType.POLEARM, 4),
    XIAO("Xiao", Element.ANEMO, WeaponType.POLEARM, 5),
    XINGQIU("Xingqiu", Element.HYDRO, WeaponType.SWORD, 4),
    XINYAN("Xinyan", Element.PYRO, WeaponType.CLAYMORE, 4),
    YANFEI("Yanfei", Element.PYRO, WeaponType.CATALYST, 4),
    ZHONGLI("Zhongli", Element.GEO, WeaponType.POLEARM, 5);

    private String name;
    private Element element;
    private WeaponType weaponType;
    private int rarity;

    // EFFECTS: constructs character with name, element, weapon type, and rarity
    Character(String name, Element element, WeaponType weaponType, int rarity) {
        this.name = name;
        this.element = element;
        this.weaponType = weaponType;
        this.rarity = rarity;
    }

    // GETTERS
    public Element getElement() {
        return this.element;
    }

    // EFFECTS: searches for Character with given name,
    //          throws IllegalCharacterException if invalid name was given
    //          returns found Character otherwise
    public static Character find(String name) throws IllegalCharacterException {
        Character result = null;
        for (Character c : values()) {
            if (c.name.equalsIgnoreCase(name)) {
                result = c;
            }
        }
        if (result == null) {
            throw new IllegalCharacterException();
        } else {
            return result;
        }
    }
}
