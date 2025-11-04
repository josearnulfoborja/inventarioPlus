import com.example.InventarioPlus.model.Rol;
import com.example.InventarioPlus.model.TipoEquipo;
import com.example.InventarioPlus.repository.TipoEquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEquipoService {
    private final TipoEquipoRepository tipoEquipoRepository;

    public TipoEquipoService(TipoEquipoRepository tipoEquipoRepository) {
        this.tipoEquipoRepository = tipoEquipoRepository;
    }

    public List<TipoEquipo> listarTipos() {
        return tipoEquipoRepository.findAll();
    }

    public Optional<TipoEquipo> obtenerTipoPorId(Integer id) {
        return tipoEquipoRepository.findById(id);
    }

    public TipoEquipo guardarTipo(TipoEquipo tipo) {
        return tipoEquipoRepository.save(tipo);
    }

    public void eliminarTipo(Integer id) {
        tipoEquipoRepository.deleteById(id);
    }
}
