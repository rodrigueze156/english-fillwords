package com.timur.englishfillwords.data

/** Одно английское слово и его перевод на русский. */
data class Word(val en: String, val ru: String)

/** Тематический набор слов одного уровня. */
data class Theme(
    val id: String,
    val nameRu: String,
    val nameEn: String,
    val emoji: String,
    val words: List<Word>
)

object Themes {

    val trees = Theme(
        id = "trees",
        nameRu = "Деревья и кусты",
        nameEn = "Trees & Shrubs",
        emoji = "🌳",
        words = listOf(
            Word("OAK", "дуб"),
            Word("MAPLE", "клён"),
            Word("BIRCH", "берёза"),
            Word("PINE", "сосна"),
            Word("WILLOW", "ива"),
            Word("CEDAR", "кедр"),
            Word("ELM", "вяз"),
            Word("POPLAR", "тополь"),
            Word("HAZEL", "орешник"),
            Word("SPRUCE", "ель"),
            Word("ALDER", "ольха"),
            Word("CHERRY", "черешня"),
            Word("WALNUT", "грецкий орех"),
            Word("ASH", "ясень"),
            Word("CYPRESS", "кипарис"),
            Word("SYCAMORE", "платан")
        )
    )

    val animals = Theme(
        id = "animals",
        nameRu = "Животные",
        nameEn = "Animals",
        emoji = "🦊",
        words = listOf(
            Word("TIGER", "тигр"),
            Word("EAGLE", "орёл"),
            Word("RABBIT", "кролик"),
            Word("DOLPHIN", "дельфин"),
            Word("PANDA", "панда"),
            Word("WOLF", "волк"),
            Word("OTTER", "выдра"),
            Word("LYNX", "рысь"),
            Word("HORSE", "лошадь"),
            Word("MONKEY", "обезьяна"),
            Word("SNAKE", "змея"),
            Word("ZEBRA", "зебра"),
            Word("BEAR", "медведь"),
            Word("FOX", "лиса"),
            Word("KOALA", "коала"),
            Word("LEOPARD", "леопард")
        )
    )

    val food = Theme(
        id = "food",
        nameRu = "Еда и напитки",
        nameEn = "Food & Drinks",
        emoji = "🍎",
        words = listOf(
            Word("BREAD", "хлеб"),
            Word("CHEESE", "сыр"),
            Word("APPLE", "яблоко"),
            Word("HONEY", "мёд"),
            Word("PEPPER", "перец"),
            Word("BUTTER", "масло"),
            Word("SUGAR", "сахар"),
            Word("ORANGE", "апельсин"),
            Word("LEMON", "лимон"),
            Word("RICE", "рис"),
            Word("SOUP", "суп"),
            Word("SALAD", "салат"),
            Word("COFFEE", "кофе"),
            Word("TOMATO", "томат"),
            Word("GARLIC", "чеснок"),
            Word("YOGURT", "йогурт")
        )
    )

    val travel = Theme(
        id = "travel",
        nameRu = "Путешествия",
        nameEn = "Travel",
        emoji = "🧭",
        words = listOf(
            Word("PASSPORT", "паспорт"),
            Word("TICKET", "билет"),
            Word("HOTEL", "отель"),
            Word("BEACH", "пляж"),
            Word("SUITCASE", "чемодан"),
            Word("ISLAND", "остров"),
            Word("CAMERA", "камера"),
            Word("TRAIN", "поезд"),
            Word("PLANE", "самолёт"),
            Word("BRIDGE", "мост"),
            Word("MARKET", "рынок"),
            Word("HARBOR", "гавань"),
            Word("GUIDE", "гид"),
            Word("LUGGAGE", "багаж"),
            Word("RESORT", "курорт"),
            Word("VOYAGE", "путешествие")
        )
    )

    val space = Theme(
        id = "space",
        nameRu = "Космос",
        nameEn = "Space",
        emoji = "🚀",
        words = listOf(
            Word("PLANET", "планета"),
            Word("COMET", "комета"),
            Word("GALAXY", "галактика"),
            Word("ROCKET", "ракета"),
            Word("ORBIT", "орбита"),
            Word("MOON", "луна"),
            Word("STAR", "звезда"),
            Word("NEBULA", "туманность"),
            Word("SATURN", "Сатурн"),
            Word("METEOR", "метеор"),
            Word("COSMOS", "космос"),
            Word("VENUS", "Венера"),
            Word("MARS", "Марс"),
            Word("EARTH", "Земля"),
            Word("SOLAR", "солнечный"),
            Word("CRATER", "кратер")
        )
    )

    val music = Theme(
        id = "music",
        nameRu = "Музыка",
        nameEn = "Music",
        emoji = "🎵",
        words = listOf(
            Word("GUITAR", "гитара"),
            Word("PIANO", "пианино"),
            Word("VIOLIN", "скрипка"),
            Word("DRUM", "барабан"),
            Word("MELODY", "мелодия"),
            Word("CHORD", "аккорд"),
            Word("OPERA", "опера"),
            Word("RHYTHM", "ритм"),
            Word("TRUMPET", "труба"),
            Word("CELLO", "виолончель"),
            Word("SONG", "песня"),
            Word("STAGE", "сцена"),
            Word("BAND", "группа"),
            Word("JAZZ", "джаз"),
            Word("NOTE", "нота"),
            Word("CHOIR", "хор")
        )
    )

    val ocean = Theme(
        id = "ocean",
        nameRu = "Океан",
        nameEn = "Ocean",
        emoji = "🐬",
        words = listOf(
            Word("CORAL", "коралл"),
            Word("SHARK", "акула"),
            Word("WHALE", "кит"),
            Word("WAVE", "волна"),
            Word("SHELL", "ракушка"),
            Word("DOLPHIN", "дельфин"),
            Word("OCTOPUS", "осьминог"),
            Word("TURTLE", "черепаха"),
            Word("ISLAND", "остров"),
            Word("CURRENT", "течение"),
            Word("LAGOON", "лагуна"),
            Word("PEARL", "жемчуг"),
            Word("STINGRAY", "скат"),
            Word("SQUID", "кальмар"),
            Word("BEACH", "пляж"),
            Word("REEF", "риф")
        )
    )

    val flowers = Theme(
        id = "flowers",
        nameRu = "Цветы",
        nameEn = "Flowers",
        emoji = "🌸",
        words = listOf(
            Word("ROSE", "роза"),
            Word("TULIP", "тюльпан"),
            Word("DAISY", "маргаритка"),
            Word("LILAC", "сирень"),
            Word("ORCHID", "орхидея"),
            Word("LILY", "лилия"),
            Word("VIOLET", "фиалка"),
            Word("PEONY", "пион"),
            Word("JASMINE", "жасмин"),
            Word("LOTUS", "лотос"),
            Word("IRIS", "ирис"),
            Word("POPPY", "мак"),
            Word("FERN", "папоротник"),
            Word("CACTUS", "кактус"),
            Word("MAGNOLIA", "магнолия"),
            Word("BEGONIA", "бегония")
        )
    )

    val weather = Theme(
        id = "weather",
        nameRu = "Погода",
        nameEn = "Weather",
        emoji = "⛅",
        words = listOf(
            Word("SUN", "солнце"),
            Word("RAIN", "дождь"),
            Word("SNOW", "снег"),
            Word("WIND", "ветер"),
            Word("STORM", "буря"),
            Word("CLOUD", "облако"),
            Word("FOG", "туман"),
            Word("FROST", "мороз"),
            Word("THUNDER", "гром"),
            Word("LIGHTNING", "молния"),
            Word("BREEZE", "бриз"),
            Word("HAIL", "град"),
            Word("HEAT", "жара"),
            Word("ICE", "лёд"),
            Word("DRIZZLE", "морось"),
            Word("RAINBOW", "радуга")
        )
    )

    val clothes = Theme(
        id = "clothes",
        nameRu = "Одежда",
        nameEn = "Clothes",
        emoji = "👕",
        words = listOf(
            Word("SHIRT", "рубашка"),
            Word("DRESS", "платье"),
            Word("JEANS", "джинсы"),
            Word("COAT", "пальто"),
            Word("SOCKS", "носки"),
            Word("BOOTS", "ботинки"),
            Word("SCARF", "шарф"),
            Word("GLOVES", "перчатки"),
            Word("HAT", "шляпа"),
            Word("SKIRT", "юбка"),
            Word("JACKET", "куртка"),
            Word("SWEATER", "свитер"),
            Word("TIE", "галстук"),
            Word("BELT", "ремень"),
            Word("SHORTS", "шорты"),
            Word("UNIFORM", "форма")
        )
    )

    val body = Theme(
        id = "body",
        nameRu = "Тело",
        nameEn = "Body",
        emoji = "🫀",
        words = listOf(
            Word("HEAD", "голова"),
            Word("HAND", "кисть"),
            Word("FOOT", "ступня"),
            Word("EYE", "глаз"),
            Word("EAR", "ухо"),
            Word("NOSE", "нос"),
            Word("MOUTH", "рот"),
            Word("HAIR", "волосы"),
            Word("ARM", "рука"),
            Word("LEG", "нога"),
            Word("FINGER", "палец"),
            Word("SHOULDER", "плечо"),
            Word("KNEE", "колено"),
            Word("BACK", "спина"),
            Word("NECK", "шея"),
            Word("HEART", "сердце")
        )
    )

    val house = Theme(
        id = "house",
        nameRu = "Дом",
        nameEn = "House",
        emoji = "🏠",
        words = listOf(
            Word("ROOF", "крыша"),
            Word("WALL", "стена"),
            Word("DOOR", "дверь"),
            Word("WINDOW", "окно"),
            Word("FLOOR", "пол"),
            Word("KITCHEN", "кухня"),
            Word("BEDROOM", "спальня"),
            Word("CHAIR", "стул"),
            Word("TABLE", "стол"),
            Word("SOFA", "диван"),
            Word("LAMP", "лампа"),
            Word("MIRROR", "зеркало"),
            Word("STAIRS", "лестница"),
            Word("GARDEN", "сад"),
            Word("GARAGE", "гараж"),
            Word("CHIMNEY", "труба")
        )
    )

    val school = Theme(
        id = "school",
        nameRu = "Школа",
        nameEn = "School",
        emoji = "🎒",
        words = listOf(
            Word("DESK", "парта"),
            Word("BOOK", "книга"),
            Word("PEN", "ручка"),
            Word("PENCIL", "карандаш"),
            Word("RULER", "линейка"),
            Word("CLASS", "класс"),
            Word("LESSON", "урок"),
            Word("TEACHER", "учитель"),
            Word("STUDENT", "ученик"),
            Word("BOARD", "доска"),
            Word("NOTEBOOK", "тетрадь"),
            Word("SUBJECT", "предмет"),
            Word("EXAM", "экзамен"),
            Word("GRADE", "оценка"),
            Word("BREAK", "перемена"),
            Word("HOMEWORK", "домашнее задание")
        )
    )

    val sport = Theme(
        id = "sport",
        nameRu = "Спорт",
        nameEn = "Sport",
        emoji = "⚽",
        words = listOf(
            Word("BALL", "мяч"),
            Word("GOAL", "гол"),
            Word("TEAM", "команда"),
            Word("COACH", "тренер"),
            Word("MATCH", "матч"),
            Word("SCORE", "счёт"),
            Word("RACE", "гонка"),
            Word("JUMP", "прыжок"),
            Word("SWIM", "плавание"),
            Word("RUN", "бег"),
            Word("TENNIS", "теннис"),
            Word("FOOTBALL", "футбол"),
            Word("HOCKEY", "хоккей"),
            Word("MEDAL", "медаль"),
            Word("TROPHY", "трофей"),
            Word("STADIUM", "стадион")
        )
    )

    val transport = Theme(
        id = "transport",
        nameRu = "Транспорт",
        nameEn = "Transport",
        emoji = "🚌",
        words = listOf(
            Word("CAR", "машина"),
            Word("BUS", "автобус"),
            Word("TRAIN", "поезд"),
            Word("PLANE", "самолёт"),
            Word("SHIP", "корабль"),
            Word("BIKE", "велосипед"),
            Word("TRUCK", "грузовик"),
            Word("TAXI", "такси"),
            Word("BOAT", "лодка"),
            Word("SCOOTER", "самокат"),
            Word("SUBWAY", "метро"),
            Word("TRAM", "трамвай"),
            Word("FERRY", "паром"),
            Word("VAN", "фургон"),
            Word("JET", "реактивный самолёт"),
            Word("WAGON", "вагон")
        )
    )

    val insects = Theme(
        id = "insects",
        nameRu = "Насекомые",
        nameEn = "Insects",
        emoji = "🐝",
        words = listOf(
            Word("ANT", "муравей"),
            Word("BEE", "пчела"),
            Word("WASP", "оса"),
            Word("FLY", "муха"),
            Word("MOTH", "мотылёк"),
            Word("BEETLE", "жук"),
            Word("SPIDER", "паук"),
            Word("BUTTERFLY", "бабочка"),
            Word("DRAGONFLY", "стрекоза"),
            Word("LADYBUG", "божья коровка"),
            Word("CRICKET", "сверчок"),
            Word("GRASSHOPPER", "кузнечик"),
            Word("MOSQUITO", "комар"),
            Word("CICADA", "цикада"),
            Word("TERMITE", "термит"),
            Word("CATERPILLAR", "гусеница")
        )
    )

    val desserts = Theme(
        id = "desserts",
        nameRu = "Сладости",
        nameEn = "Desserts",
        emoji = "🍰",
        words = listOf(
            Word("CAKE", "торт"),
            Word("PIE", "пирог"),
            Word("COOKIE", "печенье"),
            Word("CANDY", "конфета"),
            Word("HONEY", "мёд"),
            Word("SUGAR", "сахар"),
            Word("CHOCOLATE", "шоколад"),
            Word("DONUT", "пончик"),
            Word("MUFFIN", "маффин"),
            Word("WAFFLE", "вафля"),
            Word("PANCAKE", "блин"),
            Word("PUDDING", "пудинг"),
            Word("JELLY", "желе"),
            Word("CARAMEL", "карамель"),
            Word("BISCUIT", "бисквит"),
            Word("MARSHMALLOW", "зефир")
        )
    )

    val professions = Theme(
        id = "professions",
        nameRu = "Профессии",
        nameEn = "Professions",
        emoji = "👷",
        words = listOf(
            Word("DOCTOR", "врач"),
            Word("NURSE", "медсестра"),
            Word("PROFESSOR", "профессор"),
            Word("PILOT", "пилот"),
            Word("CHEF", "повар"),
            Word("DRIVER", "водитель"),
            Word("ARTIST", "художник"),
            Word("WRITER", "писатель"),
            Word("SINGER", "певец"),
            Word("ACTOR", "актёр"),
            Word("LAWYER", "юрист"),
            Word("FARMER", "фермер"),
            Word("ENGINEER", "инженер"),
            Word("PAINTER", "маляр"),
            Word("GUARD", "охранник"),
            Word("BAKER", "пекарь")
        )
    )

    val fruits = Theme(
        id = "fruits",
        nameRu = "Фрукты",
        nameEn = "Fruits",
        emoji = "🍎",
        words = listOf(
            Word("APPLE", "яблоко"),
            Word("BANANA", "банан"),
            Word("ORANGE", "апельсин"),
            Word("GRAPE", "виноград"),
            Word("PEACH", "персик"),
            Word("PEAR", "груша"),
            Word("PLUM", "слива"),
            Word("CHERRY", "черешня"),
            Word("LEMON", "лимон"),
            Word("MANGO", "манго"),
            Word("MELON", "дыня"),
            Word("KIWI", "киви"),
            Word("POMEGRANATE", "гранат"),
            Word("APRICOT", "абрикос"),
            Word("COCONUT", "кокос"),
            Word("PINEAPPLE", "ананас")
        )
    )

    val vegetables = Theme(
        id = "vegetables",
        nameRu = "Овощи",
        nameEn = "Vegetables",
        emoji = "🥕",
        words = listOf(
            Word("CARROT", "морковь"),
            Word("POTATO", "картофель"),
            Word("TOMATO", "помидор"),
            Word("ONION", "лук"),
            Word("GARLIC", "чеснок"),
            Word("CUCUMBER", "огурец"),
            Word("PEPPER", "перец"),
            Word("CABBAGE", "капуста"),
            Word("PUMPKIN", "тыква"),
            Word("BEET", "свёкла"),
            Word("RADISH", "редис"),
            Word("LETTUCE", "салат"),
            Word("SPINACH", "шпинат"),
            Word("BROCCOLI", "брокколи"),
            Word("CORN", "кукуруза"),
            Word("EGGPLANT", "баклажан")
        )
    )

    val colors = Theme(
        id = "colors",
        nameRu = "Цвета",
        nameEn = "Colors",
        emoji = "🎨",
        words = listOf(
            Word("RED", "красный"),
            Word("BLUE", "синий"),
            Word("GREEN", "зелёный"),
            Word("YELLOW", "жёлтый"),
            Word("BLACK", "чёрный"),
            Word("WHITE", "белый"),
            Word("ORANGE", "оранжевый"),
            Word("PURPLE", "фиолетовый"),
            Word("PINK", "розовый"),
            Word("BROWN", "коричневый"),
            Word("GREY", "серый"),
            Word("GOLD", "золотой"),
            Word("SILVER", "серебряный"),
            Word("BEIGE", "бежевый"),
            Word("VIOLET", "сиреневый"),
            Word("TURQUOISE", "бирюзовый")
        )
    )

    val shapes = Theme(
        id = "shapes",
        nameRu = "Формы",
        nameEn = "Shapes",
        emoji = "🔷",
        words = listOf(
            Word("CIRCLE", "круг"),
            Word("SQUARE", "квадрат"),
            Word("TRIANGLE", "треугольник"),
            Word("RECTANGLE", "прямоугольник"),
            Word("OVAL", "овал"),
            Word("STAR", "звезда"),
            Word("HEART", "сердце"),
            Word("DIAMOND", "ромб"),
            Word("CUBE", "куб"),
            Word("SPHERE", "сфера"),
            Word("CONE", "конус"),
            Word("CYLINDER", "цилиндр"),
            Word("PYRAMID", "пирамида"),
            Word("LINE", "линия"),
            Word("CROSS", "крест"),
            Word("RING", "кольцо")
        )
    )

    val kitchen = Theme(
        id = "kitchen",
        nameRu = "Кухня",
        nameEn = "Kitchen",
        emoji = "🍳",
        words = listOf(
            Word("PLATE", "тарелка"),
            Word("BOWL", "миска"),
            Word("CUP", "чашка"),
            Word("MUG", "кружка"),
            Word("SPOON", "ложка"),
            Word("FORK", "вилка"),
            Word("KNIFE", "нож"),
            Word("PAN", "сковорода"),
            Word("POT", "кастрюля"),
            Word("KETTLE", "чайник"),
            Word("OVEN", "духовка"),
            Word("FRIDGE", "холодильник"),
            Word("SINK", "раковина"),
            Word("TOWEL", "полотенце"),
            Word("TEAPOT", "заварник"),
            Word("GRATER", "тёрка")
        )
    )

    val tools = Theme(
        id = "tools",
        nameRu = "Инструменты",
        nameEn = "Tools",
        emoji = "🔧",
        words = listOf(
            Word("HAMMER", "молоток"),
            Word("NAIL", "гвоздь"),
            Word("SCREW", "винт"),
            Word("WRENCH", "гаечный ключ"),
            Word("PLIERS", "плоскогубцы"),
            Word("SAW", "пила"),
            Word("DRILL", "дрель"),
            Word("FILE", "напильник"),
            Word("LEVEL", "уровень"),
            Word("TAPE", "лента"),
            Word("CHISEL", "стамеска"),
            Word("CLAMP", "струбцина"),
            Word("RULER", "линейка"),
            Word("LADDER", "стремянка"),
            Word("AXE", "топор"),
            Word("SHOVEL", "лопата")
        )
    )

    val birds = Theme(
        id = "birds",
        nameRu = "Птицы",
        nameEn = "Birds",
        emoji = "🐦",
        words = listOf(
            Word("EAGLE", "орёл"),
            Word("SPARROW", "воробей"),
            Word("ROBIN", "малиновка"),
            Word("PIGEON", "голубь"),
            Word("OWL", "сова"),
            Word("CROW", "ворона"),
            Word("DUCK", "утка"),
            Word("SWAN", "лебедь"),
            Word("GOOSE", "гусь"),
            Word("TURKEY", "индюк"),
            Word("PARROT", "попугай"),
            Word("FALCON", "сокол"),
            Word("HERON", "цапля"),
            Word("STORK", "аист"),
            Word("PENGUIN", "пингвин"),
            Word("OSTRICH", "страус")
        )
    )

    val fish = Theme(
        id = "fish",
        nameRu = "Рыбы",
        nameEn = "Fish",
        emoji = "🐟",
        words = listOf(
            Word("SALMON", "лосось"),
            Word("TROUT", "форель"),
            Word("SHARK", "акула"),
            Word("TUNA", "тунец"),
            Word("COD", "треска"),
            Word("HERRING", "сельдь"),
            Word("CARP", "карп"),
            Word("EEL", "угорь"),
            Word("RAY", "скат"),
            Word("BASS", "окунь"),
            Word("PIKE", "щука"),
            Word("MACKEREL", "макрель"),
            Word("SARDINE", "сардина"),
            Word("ANCHOVY", "анчоус"),
            Word("CATFISH", "сом"),
            Word("GOLDFISH", "золотая рыбка")
        )
    )

    val toys = Theme(
        id = "toys",
        nameRu = "Игрушки",
        nameEn = "Toys",
        emoji = "🧸",
        words = listOf(
            Word("DOLL", "кукла"),
            Word("BALL", "мяч"),
            Word("BLOCKS", "кубики"),
            Word("PUZZLE", "пазл"),
            Word("TEDDY", "мишка"),
            Word("KITE", "воздушный змей"),
            Word("TRAIN", "поезд"),
            Word("ROBOT", "робот"),
            Word("DRUM", "барабан"),
            Word("YOYO", "йо-йо"),
            Word("TOP", "волчок"),
            Word("RATTLE", "погремушка"),
            Word("MARBLES", "шарики"),
            Word("CHESS", "шахматы"),
            Word("LEGO", "конструктор"),
            Word("WHISTLE", "свисток")
        )
    )

    val feelings = Theme(
        id = "feelings",
        nameRu = "Чувства",
        nameEn = "Feelings",
        emoji = "😊",
        words = listOf(
            Word("HAPPY", "радость"),
            Word("SAD", "грусть"),
            Word("ANGRY", "злость"),
            Word("JOY", "веселье"),
            Word("FEAR", "страх"),
            Word("LOVE", "любовь"),
            Word("HOPE", "надежда"),
            Word("CALM", "спокойствие"),
            Word("PROUD", "гордость"),
            Word("SHY", "стеснение"),
            Word("BORED", "скука"),
            Word("EXCITED", "восторг"),
            Word("NERVOUS", "волнение"),
            Word("JEALOUS", "зависть"),
            Word("GRATEFUL", "благодарность"),
            Word("LONELY", "одиночество")
        )
    )

    val drinks = Theme(
        id = "drinks",
        nameRu = "Напитки",
        nameEn = "Drinks",
        emoji = "☕",
        words = listOf(
            Word("WATER", "вода"),
            Word("TEA", "чай"),
            Word("COFFEE", "кофе"),
            Word("JUICE", "сок"),
            Word("MILK", "молоко"),
            Word("COCOA", "какао"),
            Word("LEMONADE", "лимонад"),
            Word("COLA", "кола"),
            Word("SODA", "газировка"),
            Word("WINE", "вино"),
            Word("BEER", "пиво"),
            Word("CIDER", "сидр"),
            Word("SHAKE", "коктейль"),
            Word("SMOOTHIE", "смузи"),
            Word("NECTAR", "нектар"),
            Word("SYRUP", "сироп")
        )
    )

    val bakery = Theme(
        id = "bakery",
        nameRu = "Выпечка",
        nameEn = "Bakery",
        emoji = "🥐",
        words = listOf(
            Word("BREAD", "хлеб"),
            Word("BUN", "булочка"),
            Word("ROLL", "рулет"),
            Word("LOAF", "буханка"),
            Word("BAGUETTE", "багет"),
            Word("CROISSANT", "круассан"),
            Word("PRETZEL", "крендель"),
            Word("BAGEL", "бублик"),
            Word("TOAST", "тост"),
            Word("DOUGH", "тесто"),
            Word("FLOUR", "мука"),
            Word("YEAST", "дрожжи"),
            Word("GLAZE", "глазурь"),
            Word("CRUMB", "крошка"),
            Word("BISCUIT", "печенье"),
            Word("SCONE", "скоун")
        )
    )

    val furniture = Theme(
        id = "furniture",
        nameRu = "Мебель",
        nameEn = "Furniture",
        emoji = "🪑",
        words = listOf(
            Word("WARDROBE", "шкаф"),
            Word("DRESSER", "комод"),
            Word("CABINET", "сервант"),
            Word("SHELF", "полка"),
            Word("STOOL", "табурет"),
            Word("BENCH", "скамейка"),
            Word("BED", "кровать"),
            Word("COUCH", "кушетка"),
            Word("ARMCHAIR", "кресло"),
            Word("DRAWER", "ящик"),
            Word("OTTOMAN", "пуф"),
            Word("CRADLE", "колыбель"),
            Word("MATTRESS", "матрас"),
            Word("RACK", "стеллаж"),
            Word("CHEST", "сундук"),
            Word("VANITY", "туалетный столик")
        )
    )

    val bathroom = Theme(
        id = "bathroom",
        nameRu = "Ванная",
        nameEn = "Bathroom",
        emoji = "🛁",
        words = listOf(
            Word("SOAP", "мыло"),
            Word("SHAMPOO", "шампунь"),
            Word("COMB", "расчёска"),
            Word("BRUSH", "щётка"),
            Word("RAZOR", "бритва"),
            Word("TUB", "ванна"),
            Word("SHOWER", "душ"),
            Word("TOILET", "унитаз"),
            Word("SPONGE", "губка"),
            Word("LOTION", "лосьон"),
            Word("PASTE", "паста"),
            Word("FLOSS", "нить"),
            Word("BUCKET", "ведро"),
            Word("DRAIN", "слив"),
            Word("TAP", "кран"),
            Word("PLUNGER", "вантуз")
        )
    )

    val garden = Theme(
        id = "garden",
        nameRu = "Сад",
        nameEn = "Garden",
        emoji = "🌱",
        words = listOf(
            Word("SEED", "семя"),
            Word("SOIL", "почва"),
            Word("SPADE", "лопата"),
            Word("RAKE", "грабли"),
            Word("HOSE", "шланг"),
            Word("POT", "горшок"),
            Word("FENCE", "забор"),
            Word("WEED", "сорняк"),
            Word("MULCH", "мульча"),
            Word("BUD", "бутон"),
            Word("BLOOM", "цветение"),
            Word("TRELLIS", "решётка"),
            Word("GREENHOUSE", "теплица"),
            Word("COMPOST", "компост"),
            Word("ORCHARD", "фруктовый сад"),
            Word("SPROUT", "росток")
        )
    )

    val farm = Theme(
        id = "farm",
        nameRu = "Ферма",
        nameEn = "Farm",
        emoji = "🚜",
        words = listOf(
            Word("BARN", "амбар"),
            Word("FIELD", "поле"),
            Word("CROP", "урожай"),
            Word("PLOW", "плуг"),
            Word("TRACTOR", "трактор"),
            Word("HARVEST", "жатва"),
            Word("WHEAT", "пшеница"),
            Word("BARLEY", "ячмень"),
            Word("OAT", "овёс"),
            Word("HAY", "сено"),
            Word("STABLE", "конюшня"),
            Word("COOP", "курятник"),
            Word("SHEEP", "овца"),
            Word("GOAT", "коза"),
            Word("HORSE", "лошадь"),
            Word("COW", "корова")
        )
    )

    val mountains = Theme(
        id = "mountains",
        nameRu = "Горы",
        nameEn = "Mountains",
        emoji = "⛰️",
        words = listOf(
            Word("PEAK", "вершина"),
            Word("RIDGE", "хребет"),
            Word("SLOPE", "склон"),
            Word("CLIFF", "обрыв"),
            Word("VALLEY", "долина"),
            Word("GLACIER", "ледник"),
            Word("SUMMIT", "пик"),
            Word("ROCK", "скала"),
            Word("STONE", "камень"),
            Word("CAVE", "пещера"),
            Word("TRAIL", "тропа"),
            Word("CAMP", "лагерь"),
            Word("ROPE", "верёвка"),
            Word("AXE", "ледоруб"),
            Word("TENT", "палатка"),
            Word("ALTITUDE", "высота")
        )
    )

    val city = Theme(
        id = "city",
        nameRu = "Город",
        nameEn = "City",
        emoji = "🏙️",
        words = listOf(
            Word("STREET", "улица"),
            Word("PARK", "парк"),
            Word("BRIDGE", "мост"),
            Word("TOWER", "башня"),
            Word("MUSEUM", "музей"),
            Word("THEATER", "театр"),
            Word("BANK", "банк"),
            Word("SHOP", "магазин"),
            Word("CAFE", "кафе"),
            Word("MARKET", "рынок"),
            Word("HOTEL", "отель"),
            Word("OFFICE", "офис"),
            Word("SQUARE", "площадь"),
            Word("AVENUE", "проспект"),
            Word("FOUNTAIN", "фонтан"),
            Word("ALLEY", "переулок")
        )
    )

    val medicine = Theme(
        id = "medicine",
        nameRu = "Медицина",
        nameEn = "Medicine",
        emoji = "💊",
        words = listOf(
            Word("PILL", "таблетка"),
            Word("DOSE", "доза"),
            Word("VACCINE", "вакцина"),
            Word("SYRINGE", "шприц"),
            Word("BANDAGE", "бинт"),
            Word("PLASTER", "пластырь"),
            Word("REMEDY", "лекарство"),
            Word("CLINIC", "клиника"),
            Word("PATIENT", "пациент"),
            Word("WARD", "палата"),
            Word("SCALPEL", "скальпель"),
            Word("TABLET", "пилюля"),
            Word("DROPS", "капли"),
            Word("THERAPY", "терапия"),
            Word("CHECKUP", "осмотр"),
            Word("SURGERY", "операция")
        )
    )

    val technology = Theme(
        id = "technology",
        nameRu = "Технологии",
        nameEn = "Technology",
        emoji = "💻",
        words = listOf(
            Word("PHONE", "телефон"),
            Word("LAPTOP", "ноутбук"),
            Word("SCREEN", "экран"),
            Word("MOUSE", "мышь"),
            Word("KEYBOARD", "клавиатура"),
            Word("CHIP", "чип"),
            Word("CABLE", "кабель"),
            Word("DRONE", "дрон"),
            Word("CODE", "код"),
            Word("BYTE", "байт"),
            Word("SERVER", "сервер"),
            Word("ROUTER", "роутер"),
            Word("PIXEL", "пиксель"),
            Word("MODEM", "модем"),
            Word("GADGET", "гаджет"),
            Word("CONSOLE", "консоль")
        )
    )

    val brawlstars = Theme(
        id = "brawlstars",
        nameRu = "Бравл Старс",
        nameEn = "Brawl Stars",
        emoji = "🚭",
        words = listOf(
            Word("BRAWLER", "боец"),
            Word("GADGET", "гаджет"),
            Word("SKIN", "скин"),
            Word("POWERUP", "усиление"),
            Word("TROPHY", "трофей"),
            Word("GEM", "драгоценный камень"),
            Word("BATTLE", "битва"),
            Word("ARENA", "арена"),
            Word("CLUB", "клуб"),
            Word("EVENT", "событие"),
            Word("STARPOINTS", "звёздные очки"),
            Word("SEASON", "сезон"),
            Word("REWARD", "награда"),
            Word("TOKEN", "жетон"),
            Word("BOSSFIGHT", "бой с боссом"),
            Word("DYNAMITE", "динамит")
        )
    )

    val myself = Theme(
        id = "myself",
        nameRu = "Родригез",
        nameEn = "Rodrigueze",
        emoji = "🫡",
        words = listOf(
            Word("RODRIGUEZ", "родригез"),
            Word("MYSELF", "я сам"),
            Word("HERO", "герой"),
            Word("LEGEND", "легенда"),
            Word("CHAMPION", "чемпион"),
            Word("VICTORY", "победа"),
            Word("COURAGE", "мужество"),
            Word("STRENGTH", "сила"),
            Word("HONOR", "честь"),
            Word("LOYALTY", "верность"),
            Word("RESPECT", "уважение"),
            Word("DREAM", "мечта"),
            Word("FREEDOM", "свобода"),
            Word("BROOKLI", "дарья"),
            Word("INSPIRATION", "вдохновение"),
            Word("PASSION", "страсть")
        )
    )
    
    val brawlers = Theme(
        id = "brawlers",
        nameRu = "Бравлеры",
        nameEn = "Brawlers",
        emoji = "🥊",
        words = listOf(
            Word("SHELLEY", "Шелли"),
            Word("NITA", "Нита"),
            Word("COLT", "Кольт"),
            Word("BULL", "Булл"),
            Word("JESSIE", "Джесси"),
            Word("BROCK", "Брок"),
            Word("DYNAMIKE", "Динамайк"),
            Word("TICK", "Тик"),
            Word("ELPRIMO", "Эль Примо"),
            Word("BARLEY", "Барли"),
            Word("PAM", "Пэм"),
            Word("TARA", "Тара"),
            Word("DARRYL", "Деррил"),
            Word("PIPER", "Пайпер"),
            Word("FRANK", "Фрэнк"),
            Word("GENE", "Джин")
        )
    )
    
    val brawlers2 = Theme(
        id = "brawlers2",
        nameRu = "Бравлеры 2",
        nameEn = "Brawlers 2",
        emoji = "🥋",
        words = listOf(
            Word("MAX", "Макс"),
            Word("MRP", "Мистер П"),
            Word("JACKY", "Джеки"),
            Word("BEA", "Би"),
            Word("SANDY", "Сэнди"),
            Word("EMZ", "Эмз"),
            Word("LOU", "Лу"),
            Word("COLETTE", "Колетт"),
            Word("BYRON", "Байрон"),
            Word("EDGAR", "Эдгар"),
            Word("GRIFF", "Грифф"),
            Word("JANET", "Джанет"),
            Word("BONNIE", "Бонни"),
            Word("OTIS", "Отис"),
            Word("STU", "Сту"),
            Word("GALE", "Гейл")
        )
    )

    val brawlers3 = Theme(
        id = "brawlers3",
        nameRu = "Бравлеры 3",
        nameEn = "Brawlers 3",
        emoji = "🏆",
        words = listOf(
            Word("COLETTE", "Колетт"),
            Word("BYRON", "Байрон"),
            Word("EDGAR", "Эдгар"),
            Word("GRIFF", "Грифф"),
            Word("JANET", "Джанет"),
            Word("BONNIE", "Бонни"),
            Word("OTIS", "Отис"),
            Word("STU", "Стю"),
            Word("BELLE", "Белль"),
            Word("SQUEAK", "Сквик"),
            Word("ASH", "Эш"),
            Word("ANGELO", "Анджело"),
            Word("RICO", "Рико"),
            Word("GROM", "Гром"),
            Word("FANG", "Фенг"),
            Word("EVE", "Ева")
        )
    )

    val localdarya = Theme(
        id = "localdarya",
        nameRu = "Локалки с Дашкой",
        nameEn = "Locals with Darya",
        emoji = "💞",
        words = listOf(
            Word("YAGODA", "ягода (ликер)"),
            Word("SLUTOV", "Шлюха Д.Г."),
            Word("FERTO", "лучшая песня"),
            Word("AKYLAS", "лучший певец"),
            Word("KUNI", "фанат куни"),
            Word("AEROSPACE", "аэрокос"),
            Word("PIROZHOK", "zxc пирог"),
            Word("GRISHA", "грiша"),
            Word("NIMESULID", "от чего?"),
            Word("CHETKI", "от деда"),
            Word("KASHAPOV", "пидорас"),
            Word("ADAPTATION", "адапт"),
            Word("MARTEEN", "семечки"),
            Word("SAMARA", "город возможностей"),
            Word("MADKID", "мэдк1д"),
            Word("CLUBROMANTIC", "клуб романтики")
        )
    )

    val minecraft = Theme(
        id = "minecraft",
        nameRu = "Майнкрафт",
        nameEn = "Minecraft",
        emoji = "⛏️",
        words = listOf(
            Word("MINECRAFT", "майнкрафт"),
            Word("CREEPER", "крипер"),
            Word("PICKAXE", "кирка"),
            Word("DIAMOND", "алмаз"),
            Word("ZOMBIE", "зомби"),
            Word("SKELETON", "скелет"),
            Word("VILLAGE", "деревня"),
            Word("PORTAL", "портал"),
            Word("TORCH", "факел"),
            Word("BLOCK", "блок"),
            Word("SWORD", "меч"),
            Word("APPLE", "яблоко"),
            Word("CAVE", "пещера"),
            Word("CRAFT", "крафт"),
            Word("SPAWN", "спавн"),
            Word("BIOME", "биом")
        )
    )

    val pirates = Theme(
        id = "pirates",
        nameRu = "Пираты",
        nameEn = "Pirates",
        emoji = "⚓",
        words = listOf(
            Word("PIRATE", "пират"),
            Word("TREASURE", "сокровище"),
            Word("COMPASS", "компас"),
            Word("ISLAND", "остров"),
            Word("PARROT", "попугай"),
            Word("CANNON", "пушка"),
            Word("ANCHOR", "якорь"),
            Word("SAIL", "парус"),
            Word("SHIP", "корабль"),
            Word("GOLD", "золото"),
            Word("MAP", "карта"),
            Word("CREW", "команда"),
            Word("SWORD", "меч"),
            Word("PLANK", "доска"),
            Word("CHEST", "сундук"),
            Word("RUM", "ром")
        )
    )

    val dinosaurs = Theme(
        id = "dinosaurs",
        nameRu = "Динозавры",
        nameEn = "Dinosaurs",
        emoji = "🦖",
        words = listOf(
            Word("RAPTOR", "раптор"),
            Word("FOSSIL", "окаменелость"),
            Word("JURASSIC", "юрский"),
            Word("EXTINCT", "вымерший"),
            Word("CLAW", "коготь"),
            Word("SCALE", "чешуя"),
            Word("HORN", "рог"),
            Word("NEST", "гнездо"),
            Word("EGG", "яйцо"),
            Word("TAIL", "хвост"),
            Word("ROAR", "рёв"),
            Word("BONE", "кость"),
            Word("MUSEUM", "музей"),
            Word("TRACK", "след"),
            Word("SPINE", "хребет"),
            Word("GIANT", "гигант")
        )
    )

    val magic = Theme(
        id = "magic",
        nameRu = "Магия",
        nameEn = "Magic",
        emoji = "✨",
        words = listOf(
            Word("WIZARD", "волшебник"),
            Word("WITCH", "ведьма"),
            Word("SPELL", "заклинание"),
            Word("MAGIC", "магия"),
            Word("WAND", "палочка"),
            Word("DRAGON", "дракон"),
            Word("FAIRY", "фея"),
            Word("CASTLE", "замок"),
            Word("CROWN", "корона"),
            Word("KNIGHT", "рыцарь"),
            Word("SWORD", "меч"),
            Word("SHIELD", "щит"),
            Word("TOWER", "башня"),
            Word("POTION", "зелье"),
            Word("CLOAK", "мантия"),
            Word("OWL", "сова")
        )
    )

    val heroes = Theme(
        id = "heroes",
        nameRu = "Супергерои",
        nameEn = "Superheroes",
        emoji = "🦸",
        words = listOf(
            Word("HERO", "герой"),
            Word("VILLAIN", "злодей"),
            Word("MASK", "маска"),
            Word("CAPE", "плащ"),
            Word("POWER", "сила"),
            Word("FLIGHT", "полёт"),
            Word("LASER", "лазер"),
            Word("SHIELD", "щит"),
            Word("ORIGIN", "происхождение"),
            Word("COMIC", "комикс"),
            Word("SIDEKICK", "напарник"),
            Word("RESCUE", "спасение"),
            Word("JUSTICE", "справедливость"),
            Word("LEGEND", "легенда"),
            Word("BATTLE", "битва"),
            Word("ARMOR", "броня")
        )
    )

    /** Отдельный праздничный уровень, не входит в кампании. */
    val halloween = Theme(
        id = "halloween",
        nameRu = "Хеллоуин",
        nameEn = "Halloween",
        emoji = "🎃",
        words = listOf(
            Word("PUMPKIN", "тыква"),
            Word("GHOST", "призрак"),
            Word("WITCH", "ведьма"),
            Word("SPIDER", "паук"),
            Word("BAT", "летучая мышь"),
            Word("CANDLE", "свеча"),
            Word("SKELETON", "скелет"),
            Word("VAMPIRE", "вампир"),
            Word("ZOMBIE", "зомби"),
            Word("WEREWOLF", "оборотень"),
            Word("COFFIN", "гроб"),
            Word("POTION", "зелье"),
            Word("CAULDRON", "котёл"),
            Word("MUMMY", "мумия"),
            Word("GRAVE", "могила"),
            Word("CANDY", "конфета")
        )
    )

    /** Порядок тем совпадает с порядком уровней внутри каждой сложности. */
    val all: List<Theme> = listOf(
        trees, animals, food, travel, space, music, ocean, flowers,
        weather, clothes, body, house, school, sport, transport, insects, desserts, professions,
        fruits, vegetables, colors, shapes, kitchen, tools, birds, fish, toys, feelings,
        drinks, bakery, furniture, bathroom, garden, farm, mountains, city, medicine, technology,
        brawlstars, myself, brawlers, brawlers2, brawlers3, localdarya,
        minecraft, pirates, dinosaurs, magic, heroes
    )
}
