package com.onefactor.oauth.user;

import com.onefactor.domain.User;
import com.onefactor.oauth.dao.jooq.tables.records.UserRecord;
import io.thedocs.soyuz.to;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import static com.onefactor.oauth.dao.jooq.Tables.USER;

/**
 * Created on 07.03.18.
 */
@AllArgsConstructor
@Repository
public class UserDao {

    private DSLContext dsl;
    private Mapper mapper;

    public User findByName(String name) {
        return dsl.select().from(USER).where(USER.NAME.eq(name)).fetchOne(mapper);
    }

    @Component
    private static class Mapper implements RecordMapper<Record, User> {

        @Override
        public User map(Record record) {
            UserRecord r = record.into(USER);

            return User.builder()
                    .id(r.getId())
                    .name(r.getName())
                    .password(r.getPassword())
                    .isEnabled(r.getIsEnabled())
                    .roles(to.list(to.list(r.getRoles().split(",")), String::trim))
                    .customerId(r.getCustomerId())
                    .build();
        }
    }

}
