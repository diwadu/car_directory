package pl.dc4b.cardirectory.entities;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

public abstract class BaseEntity implements Auditable {

    private int id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static int getNumberOfFields() {
        // Exclude the "id" field from the count
        return BaseEntity.class.getDeclaredFields().length - 1;
    }

    public int getNumberOfFieldsDynamic() {
        // Exclude the "id" field from the count
        return BaseEntity.class.getDeclaredFields().length - 1;
    }

    public static String getFieldName(int index) {
        Field[] fields = BaseEntity.class.getDeclaredFields();

        // Exclude the "id" field
        if (index < 1 || index >= fields.length) {
            throw new IllegalArgumentException("Invalid field index");
        }

        return fields[index].getName();
    }
}
