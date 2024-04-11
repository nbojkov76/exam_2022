package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.LibraryMembersSeedDto;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {

    private static final String LIBRARY_PATH ="src/main/resources/files/json/library-members.json";
    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final LibraryMemberRepository libraryMemberRepository;

    private final ModelMapper modelMapper;

    public LibraryMemberServiceImpl(Gson gson, ValidationUtil validationUtil, LibraryMemberRepository libraryMemberRepository, ModelMapper modelMapper) {
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.libraryMemberRepository = libraryMemberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(Path.of(LIBRARY_PATH));
    }

    @Override
    public String importLibraryMembers() throws IOException {
        
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(readLibraryMembersFileContent(), LibraryMembersSeedDto[].class))
                .filter(libraryMembersSeedDto -> {
               boolean isValid = validationUtil.isValid(libraryMembersSeedDto)
                       && !isPhoneNumberExsist(libraryMembersSeedDto.getPhoneNumber());
                        sb.append(isValid ? String.format("Successfully imported library member %s - %s"
                                ,libraryMembersSeedDto.getFirstName(), libraryMembersSeedDto.getLastName())
                                : "Invalid library member").append(System.lineSeparator());
                    return isValid;
                }).map(libraryMembersSeedDto -> modelMapper.map(libraryMembersSeedDto, LibraryMember.class))
                .forEach(libraryMemberRepository::save);
        return sb.toString();
    }

    @Override
    public boolean isExistByGivenId(Long id) {
        return libraryMemberRepository.existsById(id);
    }

    @Override
    public LibraryMember findById(long id) {
        return libraryMemberRepository.findById(id).orElse(null);
    }


    private boolean isPhoneNumberExsist(String phoneNumber) {
         return  libraryMemberRepository.existsByPhoneNumber(phoneNumber);
    }




}
