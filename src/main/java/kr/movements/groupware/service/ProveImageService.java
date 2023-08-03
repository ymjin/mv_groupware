package kr.movements.groupware.service;

import kr.movements.groupware.domain.Employee;
import kr.movements.groupware.domain.ProveImage;
import kr.movements.groupware.repository.EmployeeRepository;
import kr.movements.groupware.repository.ProveImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.math.BigInteger;
import java.sql.Blob;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProveImageService {
    private final ProveImageRepository proveImageRepository;
    private final EmployeeRepository employeeRepository;

    public void saveImage(Map<String, Object> param) {
        Optional<Employee> employeeOptional = employeeRepository.findByName((String) param.get("name"));

        if (employeeOptional.stream().count() != 0) {
            ProveImage proveImage = new ProveImage();
            proveImage.setOriginalName((String)param.get("file_name"));

            long curTime = System.currentTimeMillis();
            proveImage.setFileName(Long.toString(curTime));
            proveImage.setOriginalName((String) param.get("file_name"));
            proveImage.setImg((byte[]) param.get("file_byte"));
            proveImage.setFileSize((Long) param.get("file_size"));
            proveImage.setEmployeeId(employeeOptional.get().getId());

            save(proveImage);
        }
    }

    @Transactional
    public int save(ProveImage proveImage) {
        proveImageRepository.save(proveImage);
        return proveImage.getId();
    }
}
