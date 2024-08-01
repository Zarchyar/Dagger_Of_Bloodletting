package com.zarchyar.dagger_of_bloodletting.item;

import java.util.UUID;

public enum AttributeUUIDs {
    SWORD(UUID.fromString("2ff02bff-77cc-4c9d-8fe3-dc9be8a3256f"), UUID.fromString("ac736c5a-588b-460a-bb63-39ba54f162e0")),
    HELMET(UUID.fromString("0adbf052-7640-458a-92ff-844a99160a76"), UUID.fromString("ad62145c-86cc-4c1b-953a-1b379995d69b")),
    CHESTPLATE(UUID.fromString("943681a0-6e0d-49dc-bab2-e9e21811b1c5"), UUID.fromString("04b323ba-2657-4e62-9c27-ee55f91e385b")),
    LEGGINGS(UUID.fromString("422e64ca-38a6-4863-b453-7df3bdfbb058"), UUID.fromString("137a385e-8ebd-4d27-84ac-0abb46b9bb11")),
    BOOTS(UUID.fromString("e8c48e04-f838-4e7b-9f07-41551684af84"), UUID.fromString("773b64fe-316b-46f5-b1a3-b26b9d0c4fbb"));

    private final UUID bloodletting;
    private final UUID soulfilling;

    public UUID getBloodletting() {
        return bloodletting;
    }

    public UUID getSoulfilling() {
        return soulfilling;
    }

    AttributeUUIDs(UUID bloodletting, UUID soulfilling) {
        this.bloodletting = bloodletting;
        this.soulfilling = soulfilling;
    }
}
