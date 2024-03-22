package hibernate.entity.meta.column;

import jakarta.persistence.*;

import java.lang.reflect.Field;

public interface EntityColumn {

    static boolean isAvailableCreateEntityColumn(final Field field) {
        return !field.isAnnotationPresent(Transient.class) && !field.isAnnotationPresent(OneToMany.class)
                && !field.isAnnotationPresent(ManyToOne.class);
    }

    static EntityColumn create(final Field field) {
        if (field.isAnnotationPresent(Id.class)) {
            return new EntityId(field);
        }
        return new EntityField(field);
    }

    String getFieldName();

    Object getFieldValue(Object entity);

    void assignFieldValue(Object instance, Object value);

    ColumnType getColumnType();

    boolean isNullable();

    boolean isId();

    GenerationType getGenerationType();
}
