package kg.end_pont.springsecurety_practic.mappers;

import kg.end_pont.springsecurety_practic.dto.UserDto;
import kg.end_pont.springsecurety_practic.entity.UserEntity;
import kg.end_pont.springsecurety_practic.entity.RoleEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toDto(UserEntity userEntity) {
        List<String> roles = userEntity.getRoleEntityList()
                .stream()
                .map(RoleEntity::getAuthority)
                .collect(Collectors.toList());

        return new UserDto()
                .setId(userEntity.getId())
                .setUsername(userEntity.getUsername())
                .setEmail(userEntity.getEmail())
                .setRoles(roles);
    }

    public static UserEntity toEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        return userEntity;
    }

    public static List<UserDto> toDtoList(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}

