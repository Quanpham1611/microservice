package quan.common.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BasicMapper {
    private final ModelMapper mapper;

    public <T, S> S convertTo(T data, Class<S> type) {
        return mapper.map(data, type);
    }

    public <T, S> List<S> convertListTo(List<T> dataList, Class<S> type) {
        return dataList.stream()
                .map(data -> mapper.map(data, type))
                .collect(Collectors.toList());
    }
    /*
    convertTo và convertListTo. Cả hai phương thức đều sử dụng một đối tượng
    ModelMapper để chuyển đổi dữ liệu từ một kiểu sang một kiểu khác.
    Phương thức convertTo nhận hai đối số: một đối tượng dữ liệu data và một đối tượng Class
    biểu diễn kiểu dữ liệu mà đối tượng dữ liệu sẽ được chuyển đổi thành.
    Phương thức sử dụng phương thức map của đối tượng ModelMapper để chuyển đổi đối
    tượng dữ liệu thành kiểu dữ liệu được chỉ định và trả về kết quả.
    Phương thức convertListTo nhận hai đối số: một danh sách các đối tượng dữ liệu dataList
    và một đối tượng Class biểu diễn kiểu dữ liệu mà các đối tượng dữ liệu trong danh sách sẽ
    được chuyển đổi thành. Phương thức sử dụng luồng Java 8 để lặp qua danh sách các đối tượng dữ
    liệu và ánh xạ từng đối tượng dữ liệu thành kiểu dữ liệu được chỉ định bằng cách sử dụng phương
    thức map của đối tượng ModelMapper. Kết quả cuối cùng là một danh sách các đối tượng đã được chuyển đổi và được trả về.
     */
}
