package Classes;
public enum TipoArquivo {
    JPEG,
    PNG,
    GIF,
    TIFF,
    BMP,
    OUTRO;

    public static TipoArquivo getTipoPorExtensao(String extensao) {
        switch (extensao.toLowerCase()) {
            case "jpg":
            case "jpeg":
                return TipoArquivo.JPEG;
            case "png":
                return TipoArquivo.PNG;
            case "gif":
                return TipoArquivo.GIF;
            case "tiff":
            case "tif":
                return TipoArquivo.TIFF;
            case "bmp":
                return TipoArquivo.BMP;
            default:
                return TipoArquivo.OUTRO;
        }
    }
}
