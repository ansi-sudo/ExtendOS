#!/usr/bin/env python3
import struct, sys
from PIL import Image, ImageOps

src, default, out = sys.argv[1:4]
hdr = bytearray(open(default, 'rb').read(138))
assert hdr[:2] == b'BM'
off, = struct.unpack_from('<I', hdr, 10)
w, h, planes, bpp, comp = struct.unpack_from('<iiHHI', hdr, 18)
assert off == 138 and bpp == 16 and w == 480 and abs(h) == 800, (off, w, h, bpp)
masks = struct.unpack_from('<III', hdr, 54) if comp == 3 else (0x7C00, 0x03E0, 0x001F)
print('compression', comp, 'masks', [hex(m) for m in masks], 'height', h)

def spec(m):
    s = (m & -m).bit_length() - 1
    return s, bin(m >> s).count('1')
(rs, rb), (gs, gb), (bs, bb) = map(spec, masks)

img = ImageOps.pad(Image.open(src).convert('RGB'), (w, abs(h)), color=(0, 0, 0))
px = img.load()
rows = range(abs(h) - 1, -1, -1) if h > 0 else range(abs(h))
data = bytearray()
for y in rows:
    for x in range(w):
        r, g, b = px[x, y]
        v = ((r >> (8 - rb)) << rs) | ((g >> (8 - gb)) << gs) | ((b >> (8 - bb)) << bs)
        data += struct.pack('<H', v)
open(out, 'wb').write(bytes(hdr) + bytes(data))
print('wrote', out, len(hdr) + len(data), 'bytes')
